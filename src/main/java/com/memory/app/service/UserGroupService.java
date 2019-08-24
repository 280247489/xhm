package com.memory.app.service;

import com.memory.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @ClassName UserGroupService
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/8/3 13:21
 */
public interface UserGroupService {
    @Transactional
    User addUserAndGroup(String userOpenId, String userUnionId, String userName, String userLogo, String parentId);

    //根据userId 查询团成员列表
    Map<String,Object> groupListByUserId(Integer start, Integer limit, String userId);
}
