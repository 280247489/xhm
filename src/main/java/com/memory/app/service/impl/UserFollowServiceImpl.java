package com.memory.app.service.impl;

import com.memory.app.repository.UserFollowRepository;
import com.memory.app.service.UserFollowService;
import com.memory.common.utils.Utils;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.User;
import com.memory.entity.UserFollow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author INS6+
 * @date 2019/7/23 19:40
 */
@Service
public class UserFollowServiceImpl implements UserFollowService {
    @Autowired
    private DaoUtils daoUtils;

    @Autowired
    private UserFollowRepository repository;

    @Override
    public UserFollow add(UserFollow userFollow){
        return repository.save(userFollow);
    }

    @Override
    public UserFollow update(UserFollow userFollow){
        return repository.save(userFollow);
    }

    @Override
    public int getUserFollowByArticleId(String articleId){
        return repository.countUserFollowByArticleIdAndIsFollow(articleId,0);
    }

    @Override
    public UserFollow getUserFollowById(String id){
        if(repository.findById(id).hashCode() != 0){
            return repository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public UserFollow getUserFollowByArticleIdAndFollowUserId(String articleId,String followUserId){

        return repository.findByFollowUserIdAndArticleId(followUserId,articleId);
    }






}


