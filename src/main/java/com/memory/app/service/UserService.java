package com.memory.app.service;

import com.memory.entity.User;

import java.util.List;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 13:46
 * @Description:
 */
public interface UserService {

    //微信注册，登录
    User login(String userUnionId, String userOpenId, String userName, String userLogo);

    //手机注册
    User register(String userTel, String password);
    //手机号登录
    User login(String userTel, String password);
    //验证手机是否存在
    User checkTel(String userTel);

    User getUserById(String id);

    List<User> getUserList();

    //同步用户关注和被关注人的粉丝数量
    void syncUserFollowAndFans(User myself,User there);

    User update(User user);
}
