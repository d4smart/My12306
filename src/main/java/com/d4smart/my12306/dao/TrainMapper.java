package com.d4smart.my12306.dao;

import com.d4smart.my12306.pojo.Train;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Train record);

    int insertSelective(Train record);

    Train selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Train record);

    int updateByPrimaryKey(Train record);

    List<Train> getTrainsByPage(@Param("beginStation") String beginStation, @Param("endStation") String endStation,
                                @Param("offset") Integer offset, @Param("limit") Integer limit);

    int getTrainCount(@Param("beginStation") String beginStation, @Param("endStation") String endStation);
}