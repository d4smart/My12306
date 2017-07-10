package com.d4smart.my12306.service;

import com.d4smart.my12306.common.Const;
import com.d4smart.my12306.common.PageInfo;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.dao.*;
import com.d4smart.my12306.pojo.*;
import com.d4smart.my12306.util.BigDecimalUtil;
import com.d4smart.my12306.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by d4smart on 2017/7/4 13:12
 */
@Service("orderService")
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TrainMapper trainMapper;

    @Autowired
    private GroupMapper groupMapper;

    public ServerResponse<PageInfo> getOrders(Integer userId, String status, String orderTime, int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;

        List<Order> orders = orderMapper.getOrdersByPage(userId, status, orderTime, offset, limit);
        int count = orderMapper.getOrderCount(userId, status, orderTime);
        PageInfo pageInfo = new PageInfo(pageNum, pageSize, count);
        pageInfo.setList(orders);

        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse<Order> get(Integer id, User user) {
        Order order = orderMapper.selectByIdAndUserId(id, user.getId());
        if(order == null) {
            return ServerResponse.createByErrorMessage("订单不存在");
        }

        return ServerResponse.createBySuccess(order);
    }

    public ServerResponse<Order> getByAdmin(Integer id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        if(order == null) {
            return ServerResponse.createByErrorMessage("订单不存在");
        }

        return ServerResponse.createBySuccess(order);
    }

    public ServerResponse<String> create(String userIds, String code, String seatType, String date, User user) {
        if(userIds == null || code == null || seatType == null || date == null) {
            return ServerResponse.createByErrorMessage("订单信息不完整");
        }

        Train train = trainMapper.selectByCode(code);
        if(train == null) {
            return ServerResponse.createByErrorMessage("车次不存在");
        }

        List<User> passengers = userMapper.selectUsersByIds(userIds);
        if(passengers.isEmpty()) {
            return ServerResponse.createByErrorMessage("添加的乘客不存在");
        }

        List<Group> groups = groupMapper.getGroupsByCodeAndSeatType(code, seatType);
        int seatCount = 0;
        for(Group group : groups) seatCount += group.getCount();
        if(passengers.size() > seatCount) {
            return ServerResponse.createByErrorMessage("车票余量不足");
        }

        // 根据座位类型和线路基础价格计算总价
        double ratio = Const.FareRatio.getRatio(seatType);
        if(ratio == 0) return ServerResponse.createByErrorMessage("不存在这种座位类型");
        BigDecimal price = BigDecimalUtil.multiply(train.getPrice().doubleValue(), ratio);

        Order order = new Order();
        order.setUserId(user.getId());
        order.setActualName(user.getActualName());
        order.setStatus(Const.OrderStatus.UNPAID.getCode());
        order.setPrice(BigDecimalUtil.multiply(price.doubleValue(), passengers.size()));
        order.setOrderTime(new Date());
        orderMapper.insertAndGetId(order);

        // 出票逻辑
        Integer orderId = order.getId();
        Date setOffDate = DateTimeUtil.strToDate(date, "yyyy-MM-dd");
        for(User passenger : passengers) {
            // 移到第一个车票数非0的车厢位置
            int i = 0;
            while(groups.get(i).getCount() == 0) ++i;
            Group group = groups.get(i);

            // 创建车票并插入
            Ticket ticket = new Ticket();
            ticket.setOrderId(orderId);
            ticket.setTrip(code);
            ticket.setDate(setOffDate);
            ticket.setCabin(group.getCabin());
            ticket.setSeat(new StringBuffer().append(group.getCount()).append("号座位").toString());
            ticket.setSeatType(seatType);
            ticket.setBeginStation(train.getBeginStation());
            ticket.setEndStation(train.getEndStation());
            ticket.setPrice(price);
            ticket.setPassengerId(passenger.getId());
            ticket.setPassengerName(passenger.getActualName());
            ticket.setPassengerType(Const.PassengerType.ADULT);
            ticket.setIdentityNumber(passenger.getIdentityNumber());
            ticket.setSaleMethod(Const.SaleMethod.ONLINE);
            ticket.setSaleTime(new Date());
            ticket.setStatus(Const.TicketStatus.UNPAID.getCode());
            ticketMapper.insert(ticket);

            // 在对应的车厢减去车票
            groups.get(i).setCount(group.getCount() - 1);
        }

        int i = 0;
        // 更新因为出票后票数为0的车厢
        while(groups.get(i).getCount() == 0) {
            groupMapper.updateCountById(groups.get(i).getId(), groups.get(i).getCount());
            ++i;
        }
        // 更新出票后票数不为0的第一个车厢
        groupMapper.updateCountById(groups.get(i).getId(), groups.get(i).getCount());

        return ServerResponse.createBySuccessMessage("订单创建成功");
    }

    public ServerResponse<String> cancel(Integer id, User user) {
        Order order = orderMapper.selectByIdAndUserId(id, user.getId());
        if(order == null) {
            return ServerResponse.createByErrorMessage("订单不存在");
        }

        if(order.getStatus() != Const.OrderStatus.UNPAID.getCode()) {
            return ServerResponse.createByErrorMessage("订单不能取消");
        }

        List<Ticket> tickets = ticketMapper.getTicketsByOrderId(order.getId());
        Map<String, Integer> refunds = new HashMap<String, Integer>();
        for(Ticket ticket : tickets) {
            ticketMapper.setTicketStatus(ticket.getId(), Const.TicketStatus.CANCELED.getCode());
            // groupMapper.increaseCountByCodeAndCabin(ticket.getTrip(), ticket.getCabin(), 1); // 对应车次的车厢车票数+1

            if(refunds.containsKey(ticket.getCabin())) {
                refunds.put(ticket.getCabin(), refunds.get(ticket.getCabin()) + 1);
            } else {
                refunds.put(ticket.getCabin(), 1);
            }
        }
        for(Map.Entry<String, Integer> entry : refunds.entrySet()) {
            groupMapper.increaseCountByCodeAndCabin(tickets.get(0).getTrip(), entry.getKey(), entry.getValue());
        }
        int count = orderMapper.setOrderStatus(id, Const.OrderStatus.CANCELED.getCode());

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("取消订单成功");
        } else {
            return ServerResponse.createByErrorMessage("取消订单失败");
        }
    }

    public ServerResponse<String> retreat(Integer id, User user) {
        Order order = orderMapper.selectByIdAndUserId(id, user.getId());
        if(order == null) {
            return ServerResponse.createByErrorMessage("订单不存在");
        }

        if(order.getStatus() != Const.OrderStatus.PAID.getCode()) {
            return ServerResponse.createByErrorMessage("订单不能退款");
        }

        List<Ticket> tickets = ticketMapper.getTicketsByOrderId(order.getId());
        Map<String, Integer> refunds = new HashMap<String, Integer>();
        for(Ticket ticket : tickets) {
            ticketMapper.setTicketStatus(ticket.getId(), Const.TicketStatus.RETREATED.getCode());

            if(refunds.containsKey(ticket.getCabin())) {
                refunds.put(ticket.getCabin(), refunds.get(ticket.getCabin()) + 1);
            } else {
                refunds.put(ticket.getCabin(), 1);
            }
        }
        for(Map.Entry<String, Integer> entry : refunds.entrySet()) {
            groupMapper.increaseCountByCodeAndCabin(tickets.get(0).getTrip(), entry.getKey(), entry.getValue());
        }
        int count = orderMapper.setOrderStatus(id, Const.OrderStatus.RETREATED.getCode());

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("订单退款成功");
        } else {
            return ServerResponse.createByErrorMessage("订单退款失败");
        }
    }

    public ServerResponse<String> pay(Integer id, Integer userId) {
        Order order = orderMapper.selectByIdAndUserId(id, userId);
        if(order == null) {
            return ServerResponse.createByErrorMessage("订单不存在");
        }

        if(order.getStatus() != Const.OrderStatus.UNPAID.getCode()) {
            return ServerResponse.createByErrorMessage("订单不能支付");
        }

        List<Ticket> tickets = ticketMapper.getTicketsByOrderId(id);
        for(Ticket ticket : tickets) {
            ticketMapper.setTicketStatus(ticket.getId(), Const.TicketStatus.AVAILABLE.getCode());
        }
        int count = orderMapper.setOrderStatus(id, Const.OrderStatus.PAID.getCode());

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("订单支付成功");
        } else {
            return ServerResponse.createByErrorMessage("订单支付失败");
        }
    }
}
