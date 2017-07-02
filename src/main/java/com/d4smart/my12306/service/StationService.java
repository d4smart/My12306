package com.d4smart.my12306.service;

import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.dao.StationMapper;
import com.d4smart.my12306.pojo.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by d4smart on 2017/7/2 12:40
 */
@Service("stationService")
public class StationService {

    @Autowired
    private StationMapper stationMapper;

    public ServerResponse<List<Station>> getStationList(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;

        List<Station> stations = stationMapper.selectStationList(offset, limit);

        return ServerResponse.createBySuccess(stations);
    }

    public ServerResponse<Station> get(Integer id) {
        Station station = stationMapper.selectByPrimaryKey(id);
        if(station == null) {
            return ServerResponse.createByErrorMessage("车站不存在");
        }

        return ServerResponse.createBySuccess(station);
    }

    public ServerResponse<String> create(Station station) {
        if(station.getName() == null || station.getCode() == null) {
            return ServerResponse.createByErrorMessage("车站信息不完整");
        }

        int count = stationMapper.insertSelective(station);

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("车站添加成功");
        } else {
            return ServerResponse.createByErrorMessage("车站添加失败");
        }
    }

    public ServerResponse<String> update(Station station) {
        int count = stationMapper.updateByPrimaryKeySelective(station);

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("车站更新成功");
        } else {
            return ServerResponse.createByErrorMessage("车站更新失败");
        }
    }

    public ServerResponse<String> delete(Integer id) {
        int count = stationMapper.deleteByPrimaryKey(id);

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("车站删除成功");
        } else {
            return ServerResponse.createByErrorMessage("车站删除失败");
        }
    }
}
