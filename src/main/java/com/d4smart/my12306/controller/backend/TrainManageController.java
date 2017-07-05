package com.d4smart.my12306.controller.backend;

import com.d4smart.my12306.common.PageInfo;
import com.d4smart.my12306.common.ResponseCode;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.pojo.Train;
import com.d4smart.my12306.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by d4smart on 2017/7/3 20:12
 */
@Controller
@RequestMapping("/manage/train")
public class TrainManageController {

    @Autowired
    private TrainService trainService;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Train> get(@RequestParam(value = "id", required = true) Integer id) {
        return trainService.get(id);
    }

    @RequestMapping(value = "get_by_lineId", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> getByLineId(@RequestParam(value = "lineId", required = true) Integer lineId,
                                                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return trainService.getByLineId(lineId, pageNum, pageSize);
    }

    @RequestMapping(value = "select", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> select(String beginStation, String endStation,
                                           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return trainService.getTrains(beginStation, endStation, pageNum, pageSize);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return trainService.getTrains(null, null, pageNum, pageSize);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> create(Train train) {
        train.setId(null);
        return trainService.create(train);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> update(Train train) {
        if(train.getId() == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        return trainService.update(train);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delete(@RequestParam(value = "id", required = true) Integer id) {
        return trainService.delete(id);
    }
}
