package com.d4smart.my12306.service;

import com.d4smart.my12306.common.Const;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.dao.UserMapper;
import com.d4smart.my12306.pojo.User;
import com.d4smart.my12306.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by d4smart on 2017/6/30 18:29
 */
@Service("userService")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public ServerResponse<String> register(User user) {
        if(user.getPhone() == null && user.getEmail() == null && user.getIdentityNumber() == null) {
            return ServerResponse.createByErrorMessage("用户信息不完整");
        }

        // 注册信息的唯一性检查
        ServerResponse serverResponse;
        if(user.getPhone() != null) {
            serverResponse = checkValid(Const.PHONE, user.getPhone());
            if(serverResponse.isFailed()) return serverResponse;
        }
        if(user.getEmail() != null) {
            serverResponse = checkValid(Const.EMAIL, user.getEmail());
            if(serverResponse.isFailed()) return serverResponse;
        }
        if(user.getIdentityNumber() != null) {
            serverResponse = checkValid(Const.IDENTITY_NUMBER, user.getIdentityNumber());
            if(serverResponse.isFailed()) return serverResponse;
        }

        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        user.setRole(Const.Role.ROLE_CUSTOMER);
        user.setStatus(Const.UserStatus.NORMAL);

        int count = userMapper.insertSelective(user);
        if(count == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }

        return ServerResponse.createBySuccessMessage("注册成功");
    }

    public ServerResponse<User> login(User login) {
        // 用户密码加密进行验证
        login.setPassword(MD5Util.MD5EncodeUtf8(login.getPassword()));
        
        User user = null;
        if(login.getPhone() != null) {
            user = selectLogin(Const.PHONE, login);
        } else if(login.getEmail() != null) {
            user = selectLogin(Const.EMAIL, login);
        } else if(login.getIdentityNumber() != null) {
            user = selectLogin(Const.IDENTITY_NUMBER, login);
        }
        
        if(user == null) {
            return ServerResponse.createByErrorMessage("用户名与密码不匹配");
        }

        userMapper.updateLastLoginTime(user.getId()); //更新最后登录时间
        user.setPassword(StringUtils.EMPTY);

        return ServerResponse.createBySuccess("登陆成功", user);
    }

    public ServerResponse<User> updateUserInfo(User user) {
        if(user.getPhone() != null && userMapper.checkPhone(user.getPhone()) > 0) {
            return ServerResponse.createByErrorMessage("手机号码已被占用");
        }
        if(user.getEmail() != null && userMapper.checkEmail(user.getEmail()) > 0) {
            return ServerResponse.createByErrorMessage("邮箱已被占用");
        }
        if(user.getIdentityNumber() != null && userMapper.checkIdentityNumber(user.getIdentityNumber()) > 0) {
            return ServerResponse.createByErrorMessage("身份证号已被占用");
        }

        User update = new User();
        update.setPhone(user.getPhone());
        update.setEmail(user.getEmail());
        update.setIdentityNumber(user.getIdentityNumber());
        update.setPassword(user.getPassword());
        update.setActualName(user.getActualName());
        update.setSex(user.getSex());

        int count = userMapper.updateByPrimaryKeySelective(update);
        if(count > 0) {
            return ServerResponse.createBySuccess("更新个人信息成功", user);
        } else {
            return ServerResponse.createByErrorMessage("更新个人信息失败");
        }
    }

    public ServerResponse<String> resetPassword(User user, String oldPassword, String newPassword) {
        // 防止横向越权
        if(userMapper.checkPassword(user.getId(), MD5Util.MD5EncodeUtf8(oldPassword)) == 0) {
            return ServerResponse.createByErrorMessage("旧密码输入错误");
        }

        user.setPassword(MD5Util.MD5EncodeUtf8(newPassword));

        if(userMapper.updateByPrimaryKeySelective(user) > 0) {
            return ServerResponse.createBySuccessMessage("密码更新成功");
        } else {
            return ServerResponse.createByErrorMessage("密码更新失败");
        }
    }

    public ServerResponse<String> checkValid(String type, String str) {
        if(StringUtils.isNotBlank(type)) {
            // 开始校验
            if(Const.PHONE.equals(type)) {
                if(userMapper.checkPhone(str) > 0) {
                    return ServerResponse.createByErrorMessage("电话号码已存在");
                }
            } else if(Const.EMAIL.equals(type)) {
                if(userMapper.checkEmail(str) > 0) {
                    return ServerResponse.createByErrorMessage("邮箱已存在");
                }
            } else if(Const.IDENTITY_NUMBER.equals(type)) {
                if(userMapper.checkIdentityNumber(str) > 0) {
                    return ServerResponse.createByErrorMessage("身份证号已存在");
                }
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }

        return ServerResponse.createBySuccessMessage("校验成功");
    }

    public User selectLogin(String type, User user) {
        // 开始校验
        if(Const.PHONE.equals(type)) {
            return userMapper.selectLoginByPhone(user.getPhone(), user.getPassword());
        } else if(Const.EMAIL.equals(type)) {
            return userMapper.selectLoginByEmail(user.getEmail(), user.getPassword());
        } else if(Const.IDENTITY_NUMBER.equals(type)) {
            return userMapper.selectLoginByIdentityNumber(user.getIdentityNumber(), user.getPassword());
        } else {
            return null;
        }
    }
}
