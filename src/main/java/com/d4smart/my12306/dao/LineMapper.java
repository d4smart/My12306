package com.d4smart.my12306.dao;

import com.d4smart.my12306.pojo.Line;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Line record);

    int insertSelective(Line record);

    Line selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Line record);

    int updateByPrimaryKey(Line record);

    List<Line> getLinesByPage(@Param("offset") Integer offset, @Param("limit") Integer limit);

    int getLineCount();
}