package com.memory.cms.service;

import com.memory.entity.SysAdmin;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 13:46
 * @Description:
 */
public interface CmsSysAdminService {

    //注册
    SysAdmin register(String name, String loginname, String password);
    //登录
    SysAdmin login(String loginname, String password);
    //验证账号是否存在
    SysAdmin checkLoginname(String loginname);

}
