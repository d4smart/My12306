package com.d4smart.my12306.dao;

import com.d4smart.my12306.pojo.Line;

public interface LineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Line record);

    int insertSelective(Line record);

    Line selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Line record);

    int updateByPrimaryKey(Line record);
}