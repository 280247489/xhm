package com.memory.cms.service.impl;

import com.memory.cms.service.CmsOrderService;
import com.memory.domain.dao.DaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CmsOrderServiceImpl
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/8/5 16:52
 */
@Service("cmsOrderService")
public class CmsOrderServiceImpl implements CmsOrderService {
    @Autowired
    private DaoUtils daoUtils;

    public Map<String,Object> findByOrder (String orderNo,String userName,String shrName,String shrPhone,Integer start,Integer limit){
        Map<String,Object> returnMap = new HashMap<>();
        StringBuffer sb = new StringBuffer(" select * from order_master om,user u where u.id = om.userId");
        return returnMap;
    }

}
