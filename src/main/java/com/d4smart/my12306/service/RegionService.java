package com.d4smart.my12306.service;

import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.dao.RegionMapper;
import com.d4smart.my12306.pojo.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by d4smart on 2017/7/5 9:29
 */
@Service("regionService")
public class RegionService {

    @Autowired
    private RegionMapper regionMapper;

    public ServerResponse<List<Region>> list() {
        List<Region> regions = regionMapper.getRegions();

        return ServerResponse.createBySuccess(regions);
    }
}
