package com.memory.app.redis;

import java.util.List;
import java.util.Map;

/**
 * @author INS6+
 * @date 2019/7/23 19:58
 */

public interface UserCollectionRedisService {

    //添加关注
    void addCollection(String userId,String guanZhuId);

    //取消关注
    void removeCollection(String userId,String guanZhuId);

    //获取某人的关注列表
    List<Map<String,Object>> queryCollectionList(String userId);



}
