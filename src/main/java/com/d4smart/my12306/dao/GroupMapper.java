package com.d4smart.my12306.dao;

import com.d4smart.my12306.pojo.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

    List<Group> getGroupsByPage(@Param("code") String code, @Param("offset") Integer offset, @Param("limit") Integer limit);

    int getGroupCount(@Param("code") String code);

    List<Group> getGroupsByCode(@Param("code") String code);
}