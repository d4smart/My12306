package com.d4smart.my12306.service;

import com.d4smart.my12306.common.PageInfo;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.dao.LineMapper;
import com.d4smart.my12306.dao.SectionMapper;
import com.d4smart.my12306.pojo.Line;
import com.d4smart.my12306.pojo.Section;
import com.d4smart.my12306.util.BigDecimalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by d4smart on 2017/7/3 11:10
 */
@Service("lineService")
public class LineService {

    @Autowired
    private LineMapper lineMapper;

    @Autowired
    private SectionMapper sectionMapper;

    public ServerResponse<Line> get(Integer id) {
        Line line = lineMapper.selectByPrimaryKey(id);
        if(line == null) {
            return ServerResponse.createByErrorMessage("线路不存在");
        }

        return ServerResponse.createBySuccess(line);
    }

    public ServerResponse<PageInfo> list(String name, String station_names, int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;

        List<Line> lines = lineMapper.getLinesByPage(name, station_names, offset, limit);
        int count = lineMapper.getLineCount(name, station_names);
        PageInfo pageInfo = new PageInfo(pageNum, pageSize, count);
        pageInfo.setList(lines);

        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse<String> create(Line line) {
        if(line.getSectionIds() == null || line.getBeginTime() == null || line.getEndTime() == null || line.getStayTimes() == null) {
            return ServerResponse.createByErrorMessage("线路信息不完整");
        }

        List<Section> sections = sectionMapper.selectSectionsByIds(line.getSectionIds());

        StringBuilder stationNames = new StringBuilder(sections.get(0).getFromStation());
        BigDecimal price = new BigDecimal("0");
        int mileage = 0;
        for(Section section : sections) {
            stationNames.append(",").append(section.getToStation());
            price = BigDecimalUtil.add(price.doubleValue(), section.getPrice().doubleValue());
            mileage += section.getMileage();
        }
        int MINUTE = 60 * 1000;
        int spendTime = (int) (line.getEndTime().getTime() - line.getBeginTime().getTime()) / MINUTE;

        line.setStationNames(stationNames.toString());
        line.setPrice(price);
        line.setMileage(mileage);
        line.setSpendTime(spendTime);

        int count = lineMapper.insert(line);

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("线路添加成功");
        } else {
            return ServerResponse.createByErrorMessage("线路添加失败");
        }
    }

    public ServerResponse<String> update(Line line) {
        Line update = new Line();

        update.setId(line.getId()); //设置要更新的线路的id
        // 设置要更新的字段（部分级联更新）
        update.setName(line.getName());
        if(line.getSectionIds() != null) {
            List<Section> sections = sectionMapper.selectSectionsByIds(line.getSectionIds());

            StringBuilder stationNames = new StringBuilder(sections.get(0).getFromStation());
            BigDecimal price = new BigDecimal("0");
            int mileage = 0;
            for(Section section : sections) {
                stationNames.append(",").append(section.getToStation());
                price = BigDecimalUtil.add(price.doubleValue(), section.getPrice().doubleValue());
                mileage += section.getMileage();
            }

            update.setSectionIds(line.getSectionIds());
            update.setStationNames(stationNames.toString());
            update.setPrice(price);
            update.setMileage(mileage);
        }
        if(line.getBeginTime() != null && line.getEndTime() != null) {
            int MINUTE = 60 * 1000;
            int spendTime = (int) (line.getEndTime().getTime() - line.getBeginTime().getTime()) / MINUTE;

            update.setBeginTime(line.getBeginTime());
            update.setEndTime(line.getEndTime());
            update.setSpendTime(spendTime);
        }
        update.setStayTimes(line.getStayTimes());

        int count = lineMapper.updateByPrimaryKeySelective(update);

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("线路更新成功");
        } else {
            return ServerResponse.createByErrorMessage("线路更新失败");
        }
    }

    public ServerResponse<String> delete(Integer id) {
        int count = lineMapper.deleteByPrimaryKey(id);

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("线路删除成功");
        } else {
            return ServerResponse.createByErrorMessage("线路删除失败");
        }
    }
}
