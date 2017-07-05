package com.d4smart.my12306.dao;

import com.d4smart.my12306.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    User selectLoginByPhone(@Param("phone") String phone, @Param("password") String password);

    User selectLoginByEmail(@Param("email") String email, @Param("password") String password);

    User selectLoginByIdentityNumber(@Param("identity_number") String identity_number, @Param("password") String password);

    int updateLastLoginTime(Integer id);

    int checkPassword(@Param("id") Integer id, @Param("password") String password);

    List<User> selectUsersByPage(@Param("name") String name, @Param("sex") String sex,
                                 @Param("identityNumber") String identityNumber,
                                 @Param("offset") Integer offset, @Param("limit") Integer limit);

    int getUserCount(@Param("name") String name, @Param("sex") String sex, @Param("identityNumber") String identityNumber);

    List<User> selectUsersByIds(@Param("userIds") String userIds);

    List<User> selectSimpleUsers(@Param("phone") String phone, @Param("identityNumber") String identityNumber,
                                 @Param("email") String email, @Param("name") String name);
}