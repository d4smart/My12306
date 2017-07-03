package com.d4smart.my12306.controller.backend;

import com.d4smart.my12306.common.PageInfo;
import com.d4smart.my12306.common.ResponseCode;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.pojo.Section;
import com.d4smart.my12306.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by d4smart on 2017/7/3 9:14
 */
@Controller
@RequestMapping("/manage/section")
public class SectionManageController {

    @Autowired
    private SectionService sectionService;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Section> get(@RequestParam(value = "id", required = true) Integer id) {
        return sectionService.get(id);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return sectionService.getSections(pageNum, pageSize);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> create(Section section) {
        section.setId(null);
        return sectionService.create(section);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> update(Section section) {
        if(section.getId() == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        return sectionService.update(section);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delete(@RequestParam(value = "id", required = true) Integer id) {
        return sectionService.delete(id);
    }
}
