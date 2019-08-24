package com.memory.app.service.impl;

import com.memory.app.repository.UserCashOutRepository;
import com.memory.app.service.UserCashOutService;
import com.memory.common.utils.Utils;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.Details;
import com.memory.entity.User;
import com.memory.entity.UserCashOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName UserCashOutServiceImpl
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/8/22 23:31
 */
@Service
public class UserCashOutServiceImpl implements UserCashOutService {

    @Autowired
    private UserCashOutRepository userCashOutRepository;

    @Autowired
    private DaoUtils daoUtils;

    @Transactional
    @Override
    public Integer addCashOut(String userId, double cashOutScore){
        Integer saveYn = 0;
        try {
            User user = (User) daoUtils.getById("User",userId);
            if (user!=null){
                double user_integral = user.getUserIntegral();
                double syjf = user_integral-cashOutScore;
                user.setUserIntegral(syjf);

                UserCashOut userCashOut = new UserCashOut();
                Date date = new Date();
                double money = cashOutScore/10;
                userCashOut.setId( Utils.idTimer.format(date)+this.getFourRandom());
                userCashOut.setUserId(userId);
                userCashOut.setCashOutScore(cashOutScore);
                userCashOut.setCashOutMoney(money);
                userCashOut.setCashOutSurplusScore(syjf);
                userCashOut.setCashOutState(0);
                userCashOut.setCreateTime(date);
                userCashOut.setDrawTime(date);
                userCashOut.setOperatorId("");

                daoUtils.save(user);
                daoUtils.save(userCashOut);
                saveYn =1;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return saveYn;
    }

    private String getFourRandom(){
        Random random = new Random();
        String fourRandom = random.nextInt(100000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++)
                fourRandom = "0" + fourRandom ;
        }
        return fourRandom;
    }

    @Override
    public Map<String,Object> cashOutList(String userId, Integer start, Integer limit){
        Map<String,Object> returnMap = new HashMap<>();
        StringBuffer sb = new StringBuffer(" FROM UserCashOut WHERE userId=:userId ");
        StringBuffer sbCount = new StringBuffer(" SELECT COUNT(*) FROM UserCashOut WHERE userId=:userId ");
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        sb.append(" ORDER BY cashOutState,createTime DESC ");
        DaoUtils.Page page = daoUtils.getPage(start, limit);
        List<Details> list = daoUtils.findByHQL(sb.toString(),map,page);
        Integer count = daoUtils.getTotalByHQL(sbCount.toString(),map);
        returnMap.put("list",list);
        returnMap.put("count",count);
        return  returnMap;
    }
}
