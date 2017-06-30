package com.d4smart.my12306.service;

import com.d4smart.my12306.common.Const;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.dao.UserMapper;
import com.d4smart.my12306.pojo.User;
import com.d4smart.my12306.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by d4smart on 2017/6/30 18:29
 */
@Service("userService")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public ServerResponse<String> register(User user) {
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
        System.out.println(user.getIdentityNumber());
        System.out.println(user.getIdentityNumber() != null);
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
}
