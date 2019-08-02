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
 * @ClassName UserFollowServiceImpl
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/8 19:33
 */
@Service("userFollowService")
public class UserFollowServiceImpl implements UserFollowService {
    @Autowired
    private DaoUtils daoUtils;

    @Autowired
    private UserFollowRepository userFollowRepository;

    @Transactional
    @Override
    public UserFollow follow (String fuId,String auId){
        //查询被关注人是否存在
        User user = (User) daoUtils.getById("User",auId);
        UserFollow userFollow = null;
        if (user != null){
            userFollow = this.getByFidAid(fuId, auId);
            if (userFollow!=null){
                if (userFollow.getIsFollow()==1){
                    userFollow.setIsFollow(0);

                }else {
                    userFollow.setIsFollow(1);
                }
            }else {
                userFollow = new UserFollow();
                userFollow.setId(Utils.generateUUID());
                userFollow.setFollowUserId(fuId);
                userFollow.setAttentionUserId(auId);
                userFollow.setIsFollow(1);
                userFollow.setCreateTime(new Date());

            }
            daoUtils.save(userFollow);
        }
        return userFollow;
    }

    public UserFollow getByFidAid(String fid,String aid){
        return userFollowRepository.findByFollowUserIdAndAttentionUserId(fid, aid);
    }
}
