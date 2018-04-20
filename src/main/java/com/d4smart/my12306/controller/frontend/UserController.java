package com.d4smart.my12306.controller.frontend;

import com.d4smart.my12306.common.Const;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.pojo.User;
import com.d4smart.my12306.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by d4smart on 2017/6/30 18:54
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user) {
        return userService.register(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(User user, HttpSession session) {
        ServerResponse<User> serverResponse = userService.login(user);
        if(serverResponse.isSuccess()) {
            session.setAttribute(Const.LOGIN_USER, serverResponse.getData());
        }

        return serverResponse;
    }

    @RequestMapping(value = "/get_user_info", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        return ServerResponse.createBySuccess(user);
    }

    @RequestMapping(value = "/update_user_info", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateUserInfo(User user, HttpSession session) {
        User login = (User) session.getAttribute(Const.LOGIN_USER);

        user.setId(login.getId()); //只能更新自己
        ServerResponse<User> serverResponse = userService.updateUserInfo(user);
        if(serverResponse.isSuccess()) {
            session.setAttribute(Const.LOGIN_USER, serverResponse.getData());
        }

        return serverResponse;
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<List<User>> select(String phone, String identityNumber, String email, String name) {
        return userService.getSimpleUsers(phone, identityNumber, email, name);
    }

    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(String oldPassword, String newPassword, HttpSession session) {
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        return userService.resetPassword(user, oldPassword, newPassword);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.LOGIN_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "/check_valid", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> checkValid(String type, String str) {
        return userService.checkValid(type, str);
    }
}
