package com.d4smart.my12306.dao;

import com.d4smart.my12306.pojo.Bureau;

import java.util.List;

public interface BureauMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bureau record);

    int insertSelective(Bureau record);

    Bureau selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bureau record);

    int updateByPrimaryKey(Bureau record);

    List<Bureau> getBureaus();
}