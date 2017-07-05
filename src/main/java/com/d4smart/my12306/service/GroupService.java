package com.d4smart.my12306.service;

import com.d4smart.my12306.common.PageInfo;
import com.d4smart.my12306.common.ServerResponse;
import com.d4smart.my12306.dao.GroupMapper;
import com.d4smart.my12306.dao.TrainMapper;
import com.d4smart.my12306.pojo.Group;
import com.d4smart.my12306.pojo.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by d4smart on 2017/7/4 9:32
 */
@Service("groupService")
public class GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private TrainMapper trainMapper;

    public ServerResponse<Group> get(Integer id) {
        Group group = groupMapper.selectByPrimaryKey(id);
        if(group == null) {
            return ServerResponse.createByErrorMessage("编组不存在");
        }

        return ServerResponse.createBySuccess(group);
    }

    public ServerResponse<PageInfo> getGroups(String code, String type, int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;

        List<Group> groups = groupMapper.getGroupsByPage(code, type, offset, limit);
        int count = groupMapper.getGroupCount(code, type);
        PageInfo pageInfo = new PageInfo(pageNum, pageSize, count);
        pageInfo.setList(groups);

        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse<String> create(Group group) {
        if(group.getCode() == null || group.getCabin() == null) {
            return ServerResponse.createByErrorMessage("编组信息不完整");
        }

        Train train = trainMapper.selectByCode(group.getCode());
        if(train == null) {
            return ServerResponse.createByErrorMessage("列车不存在");
        }

        int count = groupMapper.insertSelective(group);

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("编组添加成功");
        } else {
            return ServerResponse.createByErrorMessage("编组添加失败");
        }
    }

    public ServerResponse<String> update(Group group) {
        if(group.getCode() != null) {
            Train train = trainMapper.selectByCode(group.getCode());
            if(train == null) {
                return ServerResponse.createByErrorMessage("列车不存在");
            }
        }

        int count = groupMapper.updateByPrimaryKeySelective(group);

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("编组更新成功");
        } else {
            return ServerResponse.createByErrorMessage("编组更新失败");
        }
    }

    public ServerResponse<String> delete(Integer id) {
        int count = groupMapper.deleteByPrimaryKey(id);

        if(count > 0) {
            return ServerResponse.createBySuccessMessage("编组删除成功");
        } else {
            return ServerResponse.createByErrorMessage("编组删除失败");
        }
    }
}
