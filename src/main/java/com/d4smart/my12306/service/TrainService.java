package com.d4smart.my12306.service;

import com.d4smart.my12306.common.PageInfo;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.dao.LineMapper;
import com.d4smart.my12306.dao.TrainMapper;
import com.d4smart.my12306.pojo.Line;
import com.d4smart.my12306.pojo.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by d4smart on 2017/7/3 20:13
 */
@Service("trainService")
public class TrainService {

    @Autowired
    private TrainMapper trainMapper;

    @Autowired
    private LineMapper lineMapper;

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
        if(train.getCode() == null || train.getLineId() == null) {
            return ServerResponse.createByErrorMessage("列车信息不完整");
        }

        Line line = lineMapper.selectByPrimaryKey(train.getLineId());
        if(line == null) {
            return ServerResponse.createByErrorMessage("列车线路不存在");
        }

        String stations[] = line.getStationNames().split(",");
        train.setBeginStation(stations[0]);
        train.setEndStation(stations[stations.length - 1]);
        train.setBeginTime(line.getBeginTime());
        train.setEndTime(line.getEndTime());
        train.setSpendTime(line.getSpendTime());
        train.setMileage(line.getMileage());

        int count = trainMapper.insertSelective(train);

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("列车添加成功");
        } else {
            return ServerResponse.createByErrorMessage("列车添加失败");
        }
    }

    public ServerResponse<String> update(Train train) {
        if(train.getLineId() != null) {
            Line line = lineMapper.selectByPrimaryKey(train.getLineId());
            if(line == null) {
                return ServerResponse.createByErrorMessage("列车线路不存在");
            }

            String stations[] = line.getStationNames().split(",");
            train.setBeginStation(stations[0]);
            train.setEndStation(stations[stations.length - 1]);
            train.setBeginTime(line.getBeginTime());
            train.setEndTime(line.getEndTime());
            train.setSpendTime(line.getSpendTime());
            train.setMileage(line.getMileage());
        }

        int count = trainMapper.updateByPrimaryKeySelective(train);

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("列车更新成功");
        } else {
            return ServerResponse.createByErrorMessage("列车更新失败");
        }
    }

    public ServerResponse<String> delete(Integer id) {
        int count = trainMapper.deleteByPrimaryKey(id);

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("列车删除成功");
        } else {
            return ServerResponse.createByErrorMessage("列车删除失败");
        }
    }
}
