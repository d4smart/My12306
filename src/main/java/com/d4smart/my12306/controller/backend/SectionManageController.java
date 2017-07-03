package com.d4smart.my12306.controller.backend;

import com.d4smart.my12306.common.Const;
import com.d4smart.my12306.common.PageInfo;
import com.d4smart.my12306.common.ResponseCode;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.pojo.Section;
import com.d4smart.my12306.pojo.User;
import com.d4smart.my12306.service.SectionService;
import com.d4smart.my12306.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by d4smart on 2017/7/3 9:14
 */
@Controller
@RequestMapping("/manage/section")
public class SectionManageController {

    @Autowired
    private SectionService sectionService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Section> get(@RequestParam(value = "id", required = true) Integer id, HttpSession session) {
        User login = (User) session.getAttribute(Const.LOGIN_USER);
        if(login == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }

        if(userService.isAdmin(login)) {
            return sectionService.get(id);
        } else {
            return ServerResponse.createByErrorMessage("没有权限");
        }
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                         HttpSession session) {
        User login = (User) session.getAttribute(Const.LOGIN_USER);
        if(login == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }

        if(userService.isAdmin(login)) {
            return sectionService.getSections(pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("没有权限");
        }
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> create(Section section, HttpSession session) {
        User login = (User) session.getAttribute(Const.LOGIN_USER);
        if(login == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }

        if(userService.isAdmin(login)) {
            section.setId(null);
            return sectionService.create(section);
        } else {
            return ServerResponse.createByErrorMessage("没有权限");
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> update(Section section, HttpSession session) {
        User login = (User) session.getAttribute(Const.LOGIN_USER);
        if(login == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }

        if(userService.isAdmin(login)) {
            if(section.getId() == null) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数不合法");
            }

            return sectionService.update(section);
        } else {
            return ServerResponse.createByErrorMessage("没有权限");
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delete(@RequestParam(value = "id", required = true) Integer id, HttpSession session) {
        User login = (User) session.getAttribute(Const.LOGIN_USER);
        if(login == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }

        if(userService.isAdmin(login)) {
            return sectionService.delete(id);
        } else {
            return ServerResponse.createByErrorMessage("没有权限");
        }
    }
}
