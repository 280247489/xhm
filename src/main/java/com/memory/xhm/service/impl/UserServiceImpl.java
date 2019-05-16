package com.memory.xhm.service.impl;

import com.memory.common.utils.Utils;
import com.memory.entity.User;
import com.memory.xhm.repository.UserRepository;
import com.memory.xhm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 13:46
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //微信注册，授权登录
    @Transactional
    @Override
    public User login(String userUnionId, String userOpenId, String userName, String userLogo) {
        User user = userRepository.findByUserOpenId(userOpenId);
        //第一次微信授权登录，注册
        if(user == null){
            user = new User(Utils.generateUUID(), "", userUnionId, userOpenId, "", userName, userLogo,
                    "", "", "", "", "", "",
                    new Date(), 0, 0, 0);

        }else{
            user.setUserLogo(userLogo);
        }
        userRepository.save(user);
        return user;
    }

    //验证手机是否存在
    @Override
    public User checkTel(String userTel) {
        User user = userRepository.findByUserTel(userTel);
        return user;
    }

    //手机号注册
    @Transactional
    @Override
    public User register(String userTel, String password) {
        User user = userRepository.findByUserTel(userTel);
        if(user == null){
            user = new User(Utils.generateUUID(), password, "", "", userTel, "", "",
                    "", "", "", "", "", "",
                    new Date(), 0, 0, 0);
            userRepository.save(user);
        }else{
            user = null;
        }
        return user;
    }

    //手机号登录
    @Override
    public User login(String userTel, String password){
        User user = userRepository.findByUserTelAndPassword(userTel, password);
        return user;
    }

}
