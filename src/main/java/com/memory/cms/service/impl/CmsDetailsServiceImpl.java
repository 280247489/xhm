package com.memory.cms.service.impl;

import com.memory.cms.service.CmsDetailsService;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.Details;
import com.memory.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName CmsDetailsServiceImpl
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/23 19:38
 */
@Service("cmsDetailsService")
public class CmsDetailsServiceImpl implements CmsDetailsService {

    @Autowired
    private DaoUtils daoUtils;

    @Override
    public void addIntegral(User user, Details details){
        try {
            daoUtils.save(user);
            daoUtils.save(details);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addDetails( Details details){
        try {
            daoUtils.save(details);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
