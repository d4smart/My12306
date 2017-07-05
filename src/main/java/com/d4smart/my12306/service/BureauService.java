package com.d4smart.my12306.service;

import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.dao.BureauMapper;
import com.d4smart.my12306.pojo.Bureau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by d4smart on 2017/7/5 9:37
 */
@Controller
@RequestMapping("/manage/bureau")
public class BureauService {

    @Autowired
    private BureauMapper bureauMapper;

    public ServerResponse<List<Bureau>> list() {
        List<Bureau> bureaus = bureauMapper.getBureaus();

        return ServerResponse.createBySuccess(bureaus);
    }
}
