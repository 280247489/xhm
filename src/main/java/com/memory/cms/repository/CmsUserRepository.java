package com.memory.cms.repository;

import com.memory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName CmsUserRepository
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/23 19:46
 */
public interface CmsUserRepository extends JpaRepository<User,String> {
    User findByIdAndUserNologinAndUserCancel(String userId,Integer userNologin,Integer UserCancel);
}
