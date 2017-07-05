package com.d4smart.my12306.service;

import com.d4smart.my12306.common.Const;
import com.d4smart.my12306.common.PageInfo;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.dao.GroupMapper;
import com.d4smart.my12306.dao.TicketMapper;
import com.d4smart.my12306.pojo.Ticket;
import com.d4smart.my12306.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by d4smart on 2017/7/4 22:19
 */
@Service("ticketService")
public class TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private GroupMapper groupMapper;

    public ServerResponse<Ticket> get(Integer id, User user) {
        Ticket ticket = ticketMapper.selectByIdAndUserId(id, user.getId());
        if(ticket == null) {
            return ServerResponse.createByErrorMessage("车票不存在");
        }

        return ServerResponse.createBySuccess(ticket);
    }

    public ServerResponse<Ticket> getByAdmin(Integer id) {
        Ticket ticket = ticketMapper.selectByPrimaryKey(id);
        if(ticket == null) {
            return ServerResponse.createByErrorMessage("车票不存在");
        }

        return ServerResponse.createBySuccess(ticket);
    }

    public ServerResponse<PageInfo> list(User user, int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;

        List<Ticket> tickets = ticketMapper.getTicketsByPage(user.getId(), offset, limit);
        int count = ticketMapper.getTicketCount(user.getId());
        PageInfo pageInfo = new PageInfo(pageNum, pageSize, count);
        pageInfo.setList(tickets);

        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse<PageInfo> getTickets(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;

        List<Ticket> tickets = ticketMapper.getTicketsByPage(null, offset, limit);
        int count = ticketMapper.getTicketCount(null);
        PageInfo pageInfo = new PageInfo(pageNum, pageSize, count);
        pageInfo.setList(tickets);

        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse<String> retreat(Integer id, User user) {
        Ticket ticket = ticketMapper.selectByIdAndUserId(id, user.getId());
        if(ticket == null) {
            return ServerResponse.createByErrorMessage("车票不存在");
        }

        if(ticket.getStatus() != Const.TicketStatus.AVAILABLE.getCode() && ticket.getStatus() != Const.TicketStatus.CHANGED.getCode()) {
            return ServerResponse.createByErrorMessage("车票不能改签");
        }

        ticketMapper.setTicketStatus(ticket.getId(), Const.TicketStatus.RETREATED.getCode());
        int count = groupMapper.increaseCountByCodeAndCabin(ticket.getTrip(), ticket.getCabin(), 1);

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("退票成功");
        } else {
            return ServerResponse.createByErrorMessage("退票失败");
        }
    }
}
