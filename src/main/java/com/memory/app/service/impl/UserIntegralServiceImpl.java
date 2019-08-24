package com.memory.app.service.impl;

import com.memory.app.service.UserIntegralService;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.Details;
import com.memory.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserIntegralServiceImpl
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/8/11 18:18
 */
@Service
public class UserIntegralServiceImpl implements UserIntegralService {

    @Autowired
    private DaoUtils daoUtils;

    @Override
    public Map<String,Object> userIntegralList(String userId, Integer type, Integer start, Integer limit){
        Map<String,Object> returnMap = new HashMap<>();
        User user = (User) daoUtils.getById("User",userId);
        StringBuffer sb = new StringBuffer(" FROM Details WHERE userId=:userId ");
        StringBuffer sbCount = new StringBuffer(" SELECT COUNT(*) FROM Details WHERE userId=:userId ");
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        if (type!=null){
            sb.append(" AND isComplete =: type");
            sbCount.append(" AND isComplete =: type");
            map.put("type",type);
        }
        sb.append(" ORDER BY createTime DESC");
        DaoUtils.Page page = daoUtils.getPage(start, limit);
        List<Details> list = daoUtils.findByHQL(sb.toString(),map,page);
        Integer count = daoUtils.getTotalByHQL(sbCount.toString(),map);
        returnMap.put("list",list);
        returnMap.put("count",count);
        returnMap.put("user",user);
        return  returnMap;
    }



}
