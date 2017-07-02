package com.d4smart.my12306.controller.backend;

import com.d4smart.my12306.common.Const;
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

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by d4smart on 2017/6/30 21:03
 */
@Controller
@RequestMapping("/manage/user/")
public class UserManageController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> add(User user, HttpSession session) {
        User login = (User) session.getAttribute(Const.LOGIN_USER);
        if(login == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }

        if(userService.isAdmin(login)) {
            user.setId(null);
            return userService.createOrUpdate(user);
        } else {
            return ServerResponse.createByErrorMessage("没有权限");
        }
    }

    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> update(User user, HttpSession session) {
        User login = (User) session.getAttribute(Const.LOGIN_USER);
        if(login == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }

        if(userService.isAdmin(login)) {
            if(user.getId() == null) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                        "参数不合法（没有传递用户id）");
            }

            return userService.createOrUpdate(user);
        } else {
            return ServerResponse.createByErrorMessage("没有权限");
        }
    }

    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<List<User>> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                           HttpSession session) {
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        if(user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }

        if(userService.isAdmin(user)) {
            return userService.getUserList(pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("没有权限");
        }
    }

    @RequestMapping(value = "get.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> get(@RequestParam(value = "id", required = true) Integer id, HttpSession session) {
        User login = (User) session.getAttribute(Const.LOGIN_USER);
        if(login == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }

        if(userService.isAdmin(login)) {
            return userService.get(id);
        } else {
            return ServerResponse.createByErrorMessage("没有权限");
        }
    }

    @RequestMapping(value = "delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delete(@RequestParam(value = "id", required = true) Integer id, HttpSession session) {
        User login = (User) session.getAttribute(Const.LOGIN_USER);
        if(login == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }

        if(userService.isAdmin(login)) {
            return userService.delete(id);
        } else {
            return ServerResponse.createByErrorMessage("没有权限");
        }
    }
}
