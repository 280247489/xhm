package com.memory.app.service.impl;

import com.memory.common.utils.Utils;
import com.memory.app.repository.UserRepository;
import com.memory.app.service.UserService;
import com.memory.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
                    new Date(), 0, 0, 0, "",0,0,0,0,0,0);
            userRepository.save(user);
        }
//        else{
//            user.setUserLogo(userLogo);
//        }

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
       /*     user = new User(Utils.generateUUID(), password, "", "", userTel, "", "",
                    "", "", "", "", "", "",
                    new Date(), 0, 0, 0, "");*/
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

    @Override
    public User getUserById(String id) {
        if(userRepository.findById(id).hashCode() != 0){
            return userRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void syncUserFollowAndFans(User myself, User there) {
        userRepository.save(myself);
        userRepository.save(there);
    }

    @Override
    public User update(User user){
        return userRepository.save(user);
    }

    @Override
    public User getUserByOpenId(String userOpenId) {
        User user = userRepository.findByUserOpenId(userOpenId);
        return user;
    }



}
