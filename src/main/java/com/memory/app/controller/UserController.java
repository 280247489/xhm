package com.memory.app.controller;

import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import com.memory.app.service.UserService;
import com.memory.entity.User;
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
@RequestMapping("userCon")
public class UserController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     * 微信授权登录，注册
     * @param userUnionId
     * @param userOpenId
     * @param userName
     * @param userLog
     * @return
     */
    @RequestMapping("wxLogin")
    public Message wxLogin(@RequestParam(name="unionid") String userUnionId, @RequestParam(name="openid") String userOpenId,
                       @RequestParam(name="name") String userName, @RequestParam(name="logo") String userLog) {
        msg = Message.success();
        User user = userService.login(userUnionId, userOpenId, userName, userLog);
        msg.setResult(user);
        msg.setMsg("授权登录成功");
        logger.info("wxLogin{ id: {} - openid: {} }", user.getId(), user.getUserOpenId());
        return msg;
    }

    /**
     * 验证手机号是否存在
     * @param userTel
     * @return
     */
    @RequestMapping("checkTel")
    public Message checkTel(@RequestParam(name="tel") String userTel)  {
        msg = Message.success();
        User user = userService.checkTel(userTel);
        if(user == null){
            msg.setRecode(0);
            msg.setMsg("手机号不存在");
        }else{
            msg.setRecode(1);
            msg.setMsg("手机号已存在");
        }
        logger.info("checkTel{ userTel: {}, user: {} }", userTel, user);
        return msg;
    }

    /**
     * 手机号注册
     * @param userTel
     * @param password
     * @return
     */
    @RequestMapping("register")
    public Message register(@RequestParam(name="tel") String userTel, @RequestParam(name="password") String password)  {
        msg = Message.success();
        User user = userService.register(userTel, password);
        if(user != null){
            msg.setRecode(0);
            msg.setMsg("注册成功");
        }else{
            msg.setRecode(1);
            msg.setMsg("注册失败，手机号已存在");
        }
        logger.info("register{ user: {} }", user);
        return msg;
    }

    /**
     * 手机号登录
     * @param userTel
     * @param password
     * @return
     */
    @RequestMapping("telLogin")
    public Message telLogin(@RequestParam(name="tel") String userTel, @RequestParam(name="password") String password) {
        msg = Message.success();
        try {
            User user = userService.login(userTel, password);
            if(user != null){
                msg.setMsg("登录成功");
                msg.setResult(user);
            }else{
                msg.setRecode(1);
                msg.setMsg("账号或密码错误");
            }
            logger.info("telLogin{ user: {} }", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
}
