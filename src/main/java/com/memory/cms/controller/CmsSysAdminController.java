package com.memory.cms.controller;

import com.memory.cms.service.CmsSysAdminService;
import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import com.memory.entity.SysAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 14:16
 * @Description:
 */
@RestController
@RequestMapping("cmsSysAdmin")
public class CmsSysAdminController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(CmsSysAdminController.class);
    @Autowired
    private CmsSysAdminService cmsSysAdminService;

    @RequestMapping("login")
    public Message login(@RequestParam(name="loginname") String loginname, @RequestParam(name="password") String password) {
        msg = Message.success();
        SysAdmin sysAdmin = cmsSysAdminService.login(loginname, password);
        msg.setResult(sysAdmin);
        msg.setMsg("登录成功");
        return msg;
    }

    @RequestMapping("register")
    public Message register(@RequestParam(name="name") String name, @RequestParam(name="loginname") String loginname, @RequestParam(name="password") String password)  {
        msg = Message.success();
        SysAdmin sysAdmin = cmsSysAdminService.register(name, loginname, password);
        if(sysAdmin != null){
            msg.setRecode(0);
            msg.setMsg("注册成功");
        }else{
            msg.setRecode(1);
            msg.setMsg("账号已存在");
        }
        return msg;
    }

}
