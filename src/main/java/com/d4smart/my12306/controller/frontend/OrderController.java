package com.d4smart.my12306.controller.frontend;

import com.d4smart.my12306.common.Const;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.pojo.User;
import com.d4smart.my12306.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by d4smart on 2017/7/4 13:11
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> create(String userIds, String code, String seatType, String date, HttpSession session) {
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        return orderService.create(userIds, code, seatType, date, user);
    }
}
