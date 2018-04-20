package com.d4smart.my12306.controller.backend;

import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.pojo.Bureau;
import com.d4smart.my12306.service.BureauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by d4smart on 2017/7/5 9:36
 */
@Controller
@RequestMapping("/manage/bureau")
public class BureauManageController {

    @Autowired
    private BureauService bureauService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<List<Bureau>> list() {
        return bureauService.list();
    }
}
