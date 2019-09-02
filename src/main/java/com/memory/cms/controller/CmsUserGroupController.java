package com.memory.cms.controller;

import com.memory.cms.service.CmsUserGroupService;
import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName CmsUserGroupController
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/9/2 9:20
 */
@RestController
@RequestMapping("cmsUserGroup")
public class CmsUserGroupController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(CmsUserGroupController.class);

    @Autowired
    private CmsUserGroupService cmsUserGroupService;

    @RequestMapping(value = "userList",method = RequestMethod.POST)
    public Message userList(@RequestParam Integer start,@RequestParam Integer limit,@RequestParam String startTime,@RequestParam String endTime,@RequestParam String userName){
        try {
            msg = Message.success();
            Map<String,Object> returnMap = cmsUserGroupService.userList(start, limit, startTime, endTime, userName);
            returnMap.put("fileUrl",this.getFileUrl());
            msg.setRecode(0);
            msg.setResult(returnMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping(value = "userGroupList",method = RequestMethod.POST)
    public Message userGroupList(@RequestParam Integer start, @RequestParam Integer limit, @RequestParam String startTime,
                                 @RequestParam String endTime, @RequestParam String userId,@RequestParam Integer type){
        try {
            msg = Message.success();
            Map<String,Object> returnMap = cmsUserGroupService.userGroupList(start, limit, startTime, endTime, userId, type);
            returnMap.put("fileUrl",this.getFileUrl());
            msg.setRecode(0);
            msg.setResult(returnMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }

}
