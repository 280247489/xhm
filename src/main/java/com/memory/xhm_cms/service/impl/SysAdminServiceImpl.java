package com.memory.xhm_cms.service.impl;

import com.memory.common.utils.Utils;
import com.memory.xhm.entity.SysAdmin;
import com.memory.xhm_cms.repository.SysAdminRepository;
import com.memory.xhm_cms.service.SysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.Date;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 13:46
 * @Description:
 */
@Service
public class SysAdminServiceImpl implements SysAdminService {

    @Autowired
    private SysAdminRepository sysAdminRepository;

    @Transient
    @Override
    public SysAdmin register(String name, String loginname, String password) {
        Date date = new Date();
        SysAdmin sysAdmin = sysAdminRepository.findByLoginname(loginname);
        if(sysAdmin == null){
            new SysAdmin(Utils.generateUUID(), loginname, password, "", name,
                    "", date, "", "", "", date, 0);
            sysAdminRepository.save(sysAdmin);
        }else{
            sysAdmin = null;
        }
        return sysAdmin;
    }

    @Override
    public SysAdmin login(String loginname, String password) {
        SysAdmin sysAdmin = sysAdminRepository.findByLoginnameAndPassword(loginname, password);
        return sysAdmin;
    }

    @Override
    public SysAdmin checkLoginname(String loginname) {
        SysAdmin sysAdmin = sysAdminRepository.findByLoginname(loginname);
        return sysAdmin;
    }
}
