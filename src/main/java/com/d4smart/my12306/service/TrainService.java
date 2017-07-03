package com.d4smart.my12306.service;

import com.d4smart.my12306.common.PageInfo;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.dao.TrainMapper;
import com.d4smart.my12306.pojo.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by d4smart on 2017/7/3 20:13
 */
@Service("sectionService")
public class TrainService {

    @Autowired
    private TrainMapper trainMapper;

    public ServerResponse<Train> get(Integer id) {
        Train train = trainMapper.selectByPrimaryKey(id);
        if(train == null) {
            return ServerResponse.createByErrorMessage("列车不存在");
        }

        return ServerResponse.createBySuccess(train);
    }

    public ServerResponse<PageInfo> getTrains(String beginStation, String endStation, int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;

        List<Train> trains = trainMapper.getTrainsByPage(beginStation, endStation, offset, limit);
        int count = trainMapper.getTrainCount(beginStation, endStation);
        PageInfo pageInfo = new PageInfo(pageNum, pageSize, count);
        pageInfo.setList(trains);

        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse<String> create(Train train) {
        return null;
    }

    public ServerResponse<String> update(Train train) {
        return null;
    }

    public ServerResponse<String> delete(Integer id) {
        return null;
    }
}
