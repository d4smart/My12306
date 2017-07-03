package com.d4smart.my12306.controller.backend;

import com.d4smart.my12306.common.PageInfo;
import com.d4smart.my12306.common.ResponseCode;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.pojo.User;
import com.d4smart.my12306.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by d4smart on 2017/6/30 21:03
 */
@Controller
@RequestMapping("/manage/user/")
public class UserManageController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> create(User user) {
        user.setId(null); // id由数据库控制
        return userService.createOrUpdate(user);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> update(User user) {
        if(user.getId() == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        return userService.createOrUpdate(user);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return userService.getUsers(pageNum, pageSize);
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> get(@RequestParam(value = "id", required = true) Integer id) {
        return userService.get(id);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delete(@RequestParam(value = "id", required = true) Integer id) {
        return userService.delete(id);
    }
}
