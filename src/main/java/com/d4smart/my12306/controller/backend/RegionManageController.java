package com.d4smart.my12306.controller.backend;

import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.pojo.Region;
import com.d4smart.my12306.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by d4smart on 2017/7/5 9:29
 */
@Controller
@RequestMapping("/manage/region")
public class RegionManageController {

    @Autowired
    private RegionService regionService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<List<Region>> list() {
        return regionService.list();
    }
}
