package com.d4smart.my12306.service;

import com.d4smart.my12306.common.Const;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.dao.*;
import com.d4smart.my12306.pojo.*;
import com.d4smart.my12306.util.BigDecimalUtil;
import com.d4smart.my12306.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private LineMapper lineMapper;

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

        BigDecimal price = lineMapper.getPriceById(train.getLineId());

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
            ticket.setPassengerName(passenger.getActualName());
            ticket.setPassengerType("成人");
            ticket.setIdentityNumber(passenger.getIdentityNumber());
            ticket.setSaleMethod("网络");
            ticket.setSaleTime(new Date());
            ticket.setStatus(1);
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
}
