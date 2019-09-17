package com.memory.cms.service.impl;

import com.memory.cms.service.CmsUserGroupService;
import com.memory.common.utils.Utils;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.User;
import com.memory.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * @ClassName CmsUserGroupServiceImpl
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/8/31 16:47
 */
@Service
public class CmsUserGroupServiceImpl implements CmsUserGroupService {

    @Autowired
    private DaoUtils daoUtils;

    @Override
    public Map<String, Object> userList(Integer start, Integer limit,String startTime, String endTime,String userName ){
        Map<String,Object> returnMap = new HashMap<>();
        StringBuffer sb =new StringBuffer( "FROM User WHERE 1=1 ");
        StringBuffer sb_count =new StringBuffer("SELECT COUNT(*) FROM User WHERE 1=1");
        Map<String, Object> map = new HashMap<>();
        if(!"".equals(userName)){
            map.put("userName", "%"+userName+"%");
            sb.append(" and userName like :userName");
            sb_count.append(" and userName like :userName");
        }
        if(!"".equals(startTime)){
            try {
                Date sTime = Utils.sf_yMd.parse(startTime);
                map.put("sTime", sTime);
                sb.append(" and userCreateTime >= :sTime");
                sb_count.append(" and userCreateTime >= :sTime");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(!"".equals(endTime)){
            try {
                Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
                c.setTime(Utils.sf_yMd.parse(endTime));
                c.add(Calendar.DATE, 1);
                map.put("eTime", c.getTime());
                sb.append(" and userCreateTime <= :eTime");
                sb_count.append(" and userCreateTime <= :eTime");
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        sb.append(" order by userCreateTime DESC");
        DaoUtils.Page page = daoUtils.getPage(start, limit);
        List<User> userList = daoUtils.findByHQL(sb.toString(), map.size()>0?map:null, page);
        returnMap.put("list", userList);
        returnMap.put("count", daoUtils.getTotalByHQL(sb_count.toString(), map));

        return returnMap;

    }

    @Override
    public Map<String, Object> userGroupList(Integer start, Integer limit, String startTime, String endTime, String userId, Integer type){
        Map<String,Object> returnMap = new HashMap<>();

        StringBuffer sb =new StringBuffer( "SELECT new com.memory.entity.model.CmsUserGroup(ug.id,u.userName,u.userLogo," +
                "u.userCreateTime,ug.parentIdOne,ug.parentIdTwo) FROM UserGroup ug,User u WHERE ug.userId = u.id ");
        StringBuffer sb_count =new StringBuffer("SELECT COUNT(*) FROM  UserGroup ug,User u WHERE ug.userId = u.id ");
        Map<String, Object> map = new HashMap<>();
        if(type==1){
            map.put("parentIdOne", userId);
            sb.append(" and ug.parentIdOne =:parentIdOne");
            sb_count.append(" and ug.parentIdOne =:parentIdOne");
        }else if(type==2){
            map.put("parentIdTwo", userId);
            sb.append(" and ug.parentIdTwo =:parentIdTwo");
            sb_count.append(" and ug.parentIdTwo =:parentIdTwo");
        }
        if(!"".equals(startTime)){
            try {
                Date sTime = Utils.sf_yMd.parse(startTime);
                map.put("sTime", sTime);
                sb.append(" and u.userCreateTime >= :sTime");
                sb_count.append(" and u.userCreateTime >= :sTime");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(!"".equals(endTime)){
            try {
                Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
                c.setTime(Utils.sf_yMd.parse(endTime));
                c.add(Calendar.DATE, 1);
                map.put("eTime", c.getTime());
                sb.append(" and u.userCreateTime <= :eTime");
                sb_count.append(" and u.userCreateTime <= :eTime");
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        sb.append(" order by u.userCreateTime DESC");
        System.out.println(sb.toString());
        DaoUtils.Page page = daoUtils.getPage(start, limit);
        List<com.memory.entity.model.CmsUserGroup> userGroupList = daoUtils.findByHQL(sb.toString(), map.size()>0?map:null, page);

        returnMap.put("list", userGroupList);
        returnMap.put("count", daoUtils.getTotalByHQL(sb_count.toString(), map));

        return returnMap;

    }

}
