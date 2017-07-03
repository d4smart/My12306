package com.d4smart.my12306.service;

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
}
