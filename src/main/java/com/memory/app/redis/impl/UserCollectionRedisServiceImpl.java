package com.memory.app.redis.impl;

import com.memory.app.redis.UserCollectionRedisService;
import com.memory.redis.config.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author INS6+
 * @date 2019/7/23 19:58
 */
@Service
public class UserCollectionRedisServiceImpl implements UserCollectionRedisService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void addCollection(String userId, String guanZhuId) {

    }

    @Override
    public void removeCollection(String userId, String guanZhuId) {

    }

    @Override
    public List<Map<String, Object>> queryCollectionList(String userId) {
        return null;
    }
}
