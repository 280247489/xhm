package com.memory.cms.service;

import java.util.Map;

/**
 * @ClassName CmsUserGroupService
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/8/31 16:47
 */
public interface CmsUserGroupService {
    Map<String, Object> userList(Integer start, Integer limit, String startTime, String endTime, String userName);

    Map<String, Object> userGroupList(Integer start, Integer limit, String startTime, String endTime, String userId, Integer type);
}
