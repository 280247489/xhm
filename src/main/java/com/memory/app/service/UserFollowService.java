package com.memory.app.service;

import com.memory.entity.UserFollow;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserFollowService
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/8 19:33
 */
public interface UserFollowService {
    @Transactional
    UserFollow follow(String fuId, String auId);
}
