package com.memory.cms.service.impl;

import com.memory.cms.repository.SysAdminRepository;
import com.memory.cms.service.CmsSysAdminService;
import com.memory.common.utils.Utils;
import com.memory.entity.SysAdmin;
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
public class CmsSysAdminServiceImpl implements CmsSysAdminService {

    @Autowired
    private SysAdminRepository sysAdminRepository;

    @Transient
    @Override
    public SysAdmin register(String name, String loginname, String password) {
        Date date = new Date();
        SysAdmin sysAdmin = sysAdminRepository.findByLoginname(loginname);
        if(sysAdmin == null){
            sysAdmin = new SysAdmin(Utils.generateUUID(), loginname, password, "", name,
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
