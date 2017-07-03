package com.d4smart.my12306.controller.backend;

import com.d4smart.my12306.common.PageInfo;
import com.d4smart.my12306.common.ResponseCode;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.pojo.Line;
import com.d4smart.my12306.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by d4smart on 2017/7/3 11:09
 */
@Controller
@RequestMapping("/manage/line")
public class LineManageController {

    @Autowired
    private LineService lineService;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Line> get(@RequestParam(value = "id", required = true) Integer id) {
        return lineService.get(id);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return lineService.list(pageNum, pageSize);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> create(Line line) {
        line.setId(null);
        return lineService.create(line);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> update(Line line) {
        if(line.getId() == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        return lineService.update(line);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delete(@RequestParam(value = "id", required = true) Integer id) {
        return lineService.delete(id);
    }
}
