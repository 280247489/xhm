package com.memory.app.repository;

import com.memory.entity.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName UserFollowRepository
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/8 19:38
 */
public interface UserFollowRepository extends JpaRepository<UserFollow,String> {
    UserFollow findByFollowUserIdAndAttentionUserId(String fid, String aid);
}
