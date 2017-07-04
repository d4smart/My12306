package com.d4smart.my12306.controller.backend;

import com.d4smart.my12306.common.PageInfo;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.pojo.Order;
import com.d4smart.my12306.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by d4smart on 2017/7/4 19:04
 */
@Controller
@RequestMapping("/manage/order")
public class OrderManageController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Order> get(@RequestParam(value = "id", required = true) Integer id) {
        return orderService.getByAdmin(id);
    }

    @RequestMapping(value = "select", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> select(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return orderService.getOrders(pageNum, pageSize);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return orderService.getOrders(pageNum, pageSize);
    }

    @RequestMapping(value = "pay", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> pay(@RequestParam(value = "id", required = true) Integer id) {
        return orderService.pay(id);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delete(@RequestParam(value = "id", required = true) Integer id) {
        return orderService.delete(id);
    }
}
