package com.memory.app.controller;

import com.memory.app.service.UserGroupService;
import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import com.memory.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserGroupController
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/8/7 9:18
 */
@RestController
@RequestMapping(value = "userGroup")
public class UserGroupController extends BaseController {

    @Autowired
    private UserGroupService userGroupService;

    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    public Message add(@RequestParam String userOpenId, @RequestParam String userUnionId, @RequestParam String userName, @RequestParam String userLogo, @RequestParam String parentId){
        try {
            msg = Message.success();
            User user = userGroupService.addUserAndGroup(userOpenId, userUnionId, userName, userLogo, parentId);
            msg.setRecode(0);
            msg.setResult(user);

        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping(value = "userGroupList",method = RequestMethod.POST)
    public Message add(@RequestParam String userId, @RequestParam Integer start, @RequestParam Integer limit){
        try {
            msg = Message.success();
            Map<String,Object> returnMap = userGroupService.groupListByUserId(start, limit, userId);
            returnMap.put("fileUrl",this.getFileUrl());
            msg.setRecode(0);
            msg.setResult(returnMap);

        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }
}
