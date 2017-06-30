package com.d4smart.my12306.controller.frontend;

import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.pojo.User;
import com.d4smart.my12306.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by d4smart on 2017/6/30 18:54
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(@RequestBody User user) {
        return userService.register(user);
    }
}
