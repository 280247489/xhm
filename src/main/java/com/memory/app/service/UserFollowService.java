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

    UserFollow add(UserFollow userFollow);

    UserFollow update(UserFollow userFollow);

    int getUserFollowByArticleId(String articleId);

    UserFollow getUserFollowById(String id);

    UserFollow getUserFollowByArticleIdAndFollowUserId(String articleId, String followUserId);
}
