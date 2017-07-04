package com.d4smart.my12306.dao;

import com.d4smart.my12306.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int insertAndGetId(Order order);

    int setOrderStatus(@Param("id") Integer id, @Param("status") Integer status);

    Order selectByIdAndUserId(@Param("id") Integer id, @Param("userId") Integer userId);

    List<Order> getOrdersByPage(@Param("userId") Integer userId, @Param("offset") Integer offset, @Param("limit") Integer limit);

    int getOrderCount(@Param("userId") Integer userId);
}