package com.memory.app.repository;

import com.memory.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName UserGroupRepository
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/27 16:29
 */
public interface UserGroupRepository extends JpaRepository<UserGroup,String> {
    UserGroup findByUserId(String userId);
}
