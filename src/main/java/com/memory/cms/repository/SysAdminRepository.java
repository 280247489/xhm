package com.memory.cms.repository;

import com.memory.entity.SysAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 13:45
 * @Description:
 */
public interface SysAdminRepository extends JpaRepository<SysAdmin, String> {
    SysAdmin findByLoginnameAndPassword(String userTel, String password);
    SysAdmin findByLoginname(String userTel);
}
