package com.memory.xhm_cms.controller;

import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import com.memory.xhm.entity.SysAdmin;
import com.memory.xhm_cms.service.SysAdminService;
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
@RequestMapping("sysAdmin")
@RestController
public class SysAdminController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(SysAdminController.class);
    @Autowired
    private SysAdminService sysAdminService;

    @RequestMapping("login")
    public Message login(@RequestParam(name="loginname") String loginname, @RequestParam(name="password") String password) {
        msg = Message.success();
        SysAdmin sysAdmin = sysAdminService.login(loginname, password);
        msg.setData(sysAdmin);
        msg.setMsg("登录成功");
        return msg;
    }

    @RequestMapping("register")
    public Message register(@RequestParam(name="name") String name, @RequestParam(name="loginname") String loginname, @RequestParam(name="password") String password)  {
        msg = Message.success();
        SysAdmin sysAdmin = sysAdminService.register(name, loginname, password);
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
