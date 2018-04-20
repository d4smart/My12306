package com.d4smart.my12306.controller.frontend;

import com.d4smart.my12306.common.PageInfo;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.pojo.Train;
import com.d4smart.my12306.service.TrainService;
import com.d4smart.my12306.vo.TrainDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by d4smart on 2017/7/4 10:58
 */
@Controller
@RequestMapping("/train")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> list(String beginStation, String endStation,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return trainService.getTrains(beginStation, endStation, pageNum, pageSize);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Train> get(@RequestParam(value = "id", required = true) Integer id) {
        return trainService.get(id);
    }

    @RequestMapping(value = "/get_by_code", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Train> getByCode(String code) {
        return trainService.getByCode(code);
    }

    @RequestMapping(value = "/get_detail_by_id", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<TrainDetailVo> getDetailById(@RequestParam(value = "id", required = true) Integer id) {
        return trainService.getDetailById(id);
    }

    @RequestMapping(value = "/get_detail_by_code", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<TrainDetailVo> getDetailByCode(@RequestParam(value = "code", required = true) String code) {
        return trainService.getDetailByCode(code);
    }
}
