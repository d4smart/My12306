package com.d4smart.my12306.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by d4smart on 2017/6/29 11:19
 */
@Controller
public class TestController {

    @RequestMapping(value = "test.do")
    public String test() {
        System.out.println("in test controller");
        return "index";
    }

//    @RequestMapping(value = "test2.do")
//    public String test2(User user) {
//        System.out.println("in test2 controller");
//        userMapper.insert(user);
//        return "index";
//    }
}
