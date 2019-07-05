package com.memory.cms.service.impl;

import com.memory.cms.service.CmsAdvertiseService;
import com.memory.common.utils.Utils;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.Advertise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CmsAdvertiseServiceImpl
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/6/29 18:13
 */
@Service("cmsAdvertiseService")
public class CmsAdvertiseServiceImpl implements CmsAdvertiseService {
    @Autowired
    private DaoUtils daoUtils;

    @Transactional
    @Override
    public Advertise add(Advertise advertise){
        daoUtils.save(advertise);
        return advertise;
    }

    @Override
    public Map<String,Object> list(Integer start, Integer limit, String name){
        Map<String,Object> returnMap = new HashMap<>();
        StringBuffer sb = new StringBuffer(" From Advertise Where 1=1 ");
        StringBuffer sbCount = new StringBuffer(" select count(*) from advertise where 1=1 ");
        Map<String,Object> map = new HashMap<>();
        if(!"".equals(name)){
            map.put("name", "%"+name+"%");
            sb.append(" and advertiseName like :name");
            sbCount.append(" and advertise_name like :name");
        }
        sb.append(" order by advertiseUpdateTime desc");
        DaoUtils.Page page = daoUtils.getPage(start, limit);
        List<Advertise> list = daoUtils.findByHQL(sb.toString(),map,page);
        Integer count = daoUtils.getTotalBySQL(sbCount.toString(),map);

        returnMap.put("list",list);
        returnMap.put("count",count);

        return  returnMap;
    }

    @Transactional
    @Override
    public Advertise online(String aid) {
        Advertise advertise = (Advertise) daoUtils.getById("Advertise", aid);
        if(advertise != null){
            if(advertise.getAdvertiseIsOnline()==0){
                advertise.setAdvertiseIsOnline(1);
            }else{
                advertise.setAdvertiseIsOnline(0);
            }
        }
        return advertise;
    }
}
