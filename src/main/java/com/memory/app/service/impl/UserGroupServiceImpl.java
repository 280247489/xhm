package com.memory.app.service.impl;

import com.memory.app.repository.UserGroupRepository;
import com.memory.app.repository.UserRepository;
import com.memory.app.service.UserGroupService;
import com.memory.common.utils.Utils;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.User;
import com.memory.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName UserGroupServiceImpl
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/8/3 13:20
 */
@Service("userGroupService")
public class UserGroupServiceImpl implements UserGroupService {
    @Autowired
    private DaoUtils daoUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Transactional
    @Override
    public User addUserAndGroup (String userOpenId,String userUnionId,String userName,String userLogo, String parentId){
        User user = userRepository.findByUserOpenId(userOpenId);
        Date date = new Date();
        //第一次微信授权登录，注册
        if(user == null){
            String userId = Utils.generateUUID();
            String one = "";
            user = new User(userId, "", userUnionId, userOpenId, "", userName, userLogo,
                    "", "", "", "", "", "",
                    date, 0, 0, 0, "",0,0,0,0,0,0);
            UserGroup userGroup = userGroupRepository.findByUserId(parentId);
            //上级在关系表中不存在记录
            if (userGroup==null){
                //创建上级记录
                userGroup = new UserGroup(Utils.generateUUID(),parentId,"","",10.0,5.0,date);
                User user1 = (User) daoUtils.getById("User",parentId);
                user1.setUserGroup(user1.getUserGroup()+1);
                userGroupRepository.save(userGroup);
                userRepository.save(user1);
            }else{//上级在关系表中存在记录
                one = userGroup.getParentIdOne();
            }
            UserGroup userGroupNew = new UserGroup(Utils.generateUUID(),userId,parentId,one,10.0,5.0,date);

            userGroupRepository.save(userGroupNew);
            userRepository.save(user);
        }

        return user;
    }

    //根据userId 查询团成员列表
    @Override
    public Map<String,Object> groupListByUserId(Integer start,Integer limit,String userId){
        Map<String,Object> returnMap = new HashMap<>();
        StringBuffer sb = new StringBuffer(" SELECT u.id,u.user_name,u.user_logo FROM user_group ug,`user` u " +
                "WHERE ug.user_id=u.id AND CONCAT(ug.parent_id_one,ug.parent_id_two) =:userId");
        StringBuffer sbCount = new StringBuffer(" SELECT COUNT(*) FROM user_group ug,`user` u " +
                "WHERE ug.user_id=u.id AND CONCAT(ug.parent_id_one,ug.parent_id_two) =:userId");
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        DaoUtils.Page page = daoUtils.getPage(start, limit);
        List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
        List<Object[]> list = daoUtils.findBySQL(sb.toString(),map,page,null);
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> objMap=new HashMap<String, Object>();
            objMap.put("id", list.get(i)[0]);
            objMap.put("userName", list.get(i)[1]);
            objMap.put("userLogo", list.get(i)[2]);
            returnList.add(objMap);
        }
        Integer count = daoUtils.getTotalBySQL(sbCount.toString(),map);
        returnMap.put("list",returnList);
        returnMap.put("count",count);
        return returnMap;
    }


}
