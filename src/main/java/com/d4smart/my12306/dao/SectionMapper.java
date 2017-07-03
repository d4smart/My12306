package com.d4smart.my12306.dao;

import com.d4smart.my12306.pojo.Section;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Section record);

    int insertSelective(Section record);

    Section selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Section record);

    int updateByPrimaryKey(Section record);

    List<Section> getSectionsByPage(@Param("fromStation") String fromStation, @Param("toStation") String toStation,
                                    @Param("offset") Integer offset, @Param("limit") Integer limit);

    int getSectionCount(@Param("fromStation") String fromStation, @Param("toStation") String toStation);

    List<Section> selectSectionsByIds(@Param("sectionIds") String sectionIds);
}