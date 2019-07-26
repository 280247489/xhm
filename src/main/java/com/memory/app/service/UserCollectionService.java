package com.memory.app.service;

import com.memory.entity.UserCollection;

import java.util.List;

/**
 * @author INS6+
 * @date 2019/7/23 19:40
 */

public interface UserCollectionService {

    UserCollection add(UserCollection userCollection);

    UserCollection update(UserCollection userCollection);

    //查询某个人的关注列表
    List<com.memory.entity.model.UserCollection> queryUserCollectionListByQue(int pageIndex,int pageLimit,String collection_user_id);

    //查询某个人关注了多少个用户
    int queryUserCollectionCountByQue(String collection_user_id);

    //查询某个人的粉丝数量
    int queryUserCollectionFansByQue(String userId);

    //根据id获取详情
    UserCollection getUserCollectionById(String id);

    //根据用户id 和 被关注人id 确认是否有重复关注
    int getUserCollectionByIdAndAttentionUserId(String userId,String attentionUserId);


    UserCollection getUserCollectionByCollectionUserIdAndAttentionUserId(String userId,String attentionUserId);


    List<com.memory.entity.model.UserCollection> queryUserFansListByQue(int pageIndex, int pageLimit, String collection_user_id);
}
