package com.d4smart.my12306.controller.frontend;

import com.d4smart.my12306.common.Const;
import com.d4smart.my12306.common.PageInfo;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.pojo.Order;
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

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> select(String status, String orderTime, HttpSession session,
                                           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        return orderService.getOrders(user.getId(), status, orderTime, pageNum, pageSize);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Order> get(@RequestParam(value = "id", required = true) Integer id, HttpSession session) {
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        return orderService.get(id, user);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> create(String userIds, String code, String seatType, String date, HttpSession session) {
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        return orderService.create(userIds, code, seatType, date, user);
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> pay(@RequestParam(value = "id", required = true) Integer id, HttpSession session) {
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        return orderService.pay(id, user.getId());
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> cancel(@RequestParam(value = "id", required = true) Integer id, HttpSession session) {
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        return orderService.cancel(id, user);
    }

    @RequestMapping(value = "/retreat", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> retreat(@RequestParam(value = "id", required = true) Integer id, HttpSession session) {
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        return orderService.retreat(id, user);
    }
}
