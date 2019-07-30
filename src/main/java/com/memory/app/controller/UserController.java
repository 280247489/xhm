package com.memory.app.controller;

import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import com.memory.app.service.UserService;
import com.memory.common.utils.Result;
import com.memory.common.utils.ResultUtil;
import com.memory.common.utils.Utils;
import com.memory.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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


    @RequestMapping("getUserInfo")
    public Result getUserInfo(@RequestParam String userId){
        Result result = new Result();
        try {
            User user = userService.getUserById(userId);
            Map<String, Object> map = new HashMap<>();
            map.put("fileUrl", this.getFileUrl());
            map.put("user", user);

            result = ResultUtil.success(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //小程序用户头像上传接口
    @RequestMapping(value = "userLogo")
    public Result userLogo(@RequestParam MultipartFile file,@RequestParam(value = "userId",required = false) String userId){
        Result result = new Result();
        String path ="";
        try {



            Date date = new Date();

                path = this.upload2PNG("logo"+"_"+Utils.idTimer.format(date), "xhm_file/user/"+userId, file);

            System.out.printf("file upload path ==="+path);

            Map<String,Object> returnMap = new HashMap <String, Object>();
            returnMap.put("uuid", "");
            returnMap.put("path", path);

            result = ResultUtil.success(returnMap);

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //更新用户信息
    @RequestMapping("upgradeUser")
    public Result upgradeUser(@RequestParam String userLogo,@RequestParam String userName,@RequestParam String userTel,
                              @RequestParam String userSex,@RequestParam String birthday,@RequestParam String userId){
        Result result = new Result();
        try {
            User user = userService.getUserById(userId);
            if(Utils.isNotNull(user)){
                if(Utils.isNotNull(userLogo)){
                    user.setUserLogo(userLogo);
                }

                if(Utils.isNotNull(userName)){
                    user.setUserName(userName);
                }

                if(Utils.isNotNull(userTel)){
                    user.setUserTel(userTel);
                }

                if(Utils.isNotNull(userSex)){
                    user.setUserSex(userSex);
                }

                if(Utils.isNotNull(birthday)){
                    user.setUserBirthday(birthday);
                }
                User resultUser = userService.update(user);
                Map<String, Object> map = new HashMap<>();
                map.put("fileUrl", this.getFileUrl());
                map.put("user", resultUser);

                result = ResultUtil.success(map);
            }else{
                result = ResultUtil.error(-1,"非法用户");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "getUserByOpenId",method = RequestMethod.POST)
    public Message getUserByOpenId(@RequestParam String userOpenId) {
        msg = Message.success();
        try {
            User user = userService.getUserByOpenId(userOpenId);
            if(user != null){
                msg.setResult(user);
            }else{
                msg.setRecode(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }




}
