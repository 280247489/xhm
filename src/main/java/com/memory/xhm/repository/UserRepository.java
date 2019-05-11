package com.memory.xhm.repository;

import com.memory.xhm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 13:45
 * @Description:
 */
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserTelAndPassword(String userTel, String password);
    User findByUserTel(String userTel);
    User findByUserOpenId(String userOpenId);
}
