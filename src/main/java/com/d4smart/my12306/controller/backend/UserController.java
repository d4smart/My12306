package com.d4smart.my12306.controller.backend;

import com.d4smart.my12306.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by d4smart on 2017/6/30 21:03
 */
@Controller
@RequestMapping("/manage/user/")
public class UserController {

    @Autowired
    private UserService userService;
}
