package com.d4smart.my12306.service;

import com.d4smart.my12306.common.PageInfo;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.dao.SectionMapper;
import com.d4smart.my12306.pojo.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by d4smart on 2017/7/3 9:15
 */
@Service("sectionService")
public class SectionService {

    @Autowired
    private SectionMapper sectionMapper;

    public ServerResponse<Section> get(Integer id) {
        Section section = sectionMapper.selectByPrimaryKey(id);
        if(section == null) {
            return ServerResponse.createByErrorMessage("区段不存在");
        }

        return ServerResponse.createBySuccess(section);
    }

    public ServerResponse<PageInfo> getSections(String fromStation, String toStation, int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;

        List<Section> sections = sectionMapper.getSectionsByPage(fromStation, toStation, offset, limit);
        int count = sectionMapper.getSectionCount(fromStation, toStation);
        PageInfo pageInfo = new PageInfo(pageNum, pageSize, count);
        pageInfo.setList(sections);

        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse<String> create(Section section) {
        if(section.getFromStation() == null || section.getToStation() == null) {
            return ServerResponse.createByErrorMessage("区段信息不完整");
        }

        int count = sectionMapper.insertSelective(section);

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("区段添加成功");
        } else {
            return ServerResponse.createByErrorMessage("区段添加失败");
        }
    }

    public ServerResponse<String> update(Section section) {
        int count = sectionMapper.updateByPrimaryKeySelective(section);

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("区段更新成功");
        } else {
            return ServerResponse.createByErrorMessage("区段更新失败");
        }
    }

    public ServerResponse<String> delete(Integer id) {
        int count = sectionMapper.deleteByPrimaryKey(id);

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("区段删除成功");
        } else {
            return ServerResponse.createByErrorMessage("区段删除失败");
        }
    }
}
