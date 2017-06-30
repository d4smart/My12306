package com.d4smart.my12306.dao;

import com.d4smart.my12306.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkPhone(String phone);

    int checkEmail(String email);

    int checkIdentityNumber(String identity_number);
}