package com.d4smart.my12306.dao;

import com.d4smart.my12306.pojo.Station;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Station record);

    int insertSelective(Station record);

    Station selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Station record);

    int updateByPrimaryKey(Station record);

    List<Station> selectStationsByPage(@Param("offset") Integer offset, @Param("limit") Integer limit);
}