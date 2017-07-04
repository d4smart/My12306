package com.d4smart.my12306.dao;

import com.d4smart.my12306.pojo.Ticket;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TicketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ticket record);

    int insertSelective(Ticket record);

    Ticket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ticket record);

    int updateByPrimaryKey(Ticket record);

    int setTicketStatus(@Param("id") Integer id, @Param("status") Integer status);

    List<Ticket> getTicketsByOrderId(Integer orderId);

    int deleteByOrderId(@Param("orderId") Integer orderId);

    Ticket selectByIdAndUserId(@Param("id") Integer id, @Param("userId") Integer userId);

    List<Ticket> getTicketsByPage(@Param("userId") Integer userId, @Param("offset") Integer offset, @Param("limit") Integer limit);

    int getTicketCount(@Param("userId") Integer userId);
}