package com.memory.app.service.impl;

import com.memory.app.repository.UserCollectionRepository;
import com.memory.app.service.UserCollectionService;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.UserCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author INS6+
 * @date 2019/7/23 19:40
 */
@Service
public class UserCollectionServiceImpl implements UserCollectionService {

    @Autowired
    private UserCollectionRepository repository;

    @Autowired
    private DaoUtils daoUtils;

    @Override
    public UserCollection add(UserCollection userCollection) {
        return repository.save(userCollection);
    }

    @Override
    public UserCollection update(UserCollection userCollection) {
        return repository.save(userCollection);
    }

    @Override
    public List<com.memory.entity.model.UserCollection> queryUserCollectionListByQue(int pageIndex,int pageLimit,String collection_user_id) {
        StringBuffer sb = new StringBuffer();
        DaoUtils.Page page = daoUtils.getPage(pageIndex, pageLimit);
        Map<String,Object> paramMap = new HashMap<String, Object>();
        /**
         *  this.id = id;
         *         this.collectionUserId = collectionUserId;
         *         this.attentionUserId = attentionUserId;
         *         this.isFollow = isFollow;
         *         this.createTime = createTime;
         *         this.userName = userName;
         *         this.userLogo = userLogo;
         *         this.userId = userId;
         */
        sb.append(" select new com.memory.entity.model.UserCollection(uc.id,uc.collectionUserId,uc.attentionUserId,uc.isFollow," +
                "uc.createTime,u.userName,u.userLogo,u.id) ");
        sb.append(" from UserCollection uc ,User u where uc.attentionUserId = u.id ");
        sb.append(" AND uc.collectionUserId =:collectionUserId");
        sb.append(" AND uc.isFollow = 1");
        sb.append(" ORDER by uc.createTime desc");
        paramMap.put("collectionUserId",collection_user_id);
        return  daoUtils.findByHQL(sb.toString(), paramMap, page);
    }

    @Override
    public int queryUserCollectionCountByQue(String collection_user_id) {

        StringBuffer sb = new StringBuffer();
        Map<String,Object> paramMap = new HashMap<String, Object>();
        sb.append(" select count(*) from UserCollection where 1=1 ");
        sb.append(" AND collectionUserId =:collectionUserId");
        sb.append(" AND isFollow = 1");
        sb.append(" ORDER by createTime desc");
        paramMap.put("collectionUserId",collection_user_id);

        return daoUtils.getTotalByHQL(sb.toString(),paramMap);
    }

    @Override
    public UserCollection getUserCollectionById(String id) {
        if(repository.findById(id).hashCode() != 0){
            return repository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public int getUserCollectionByIdAndAttentionUserId(String userId, String attentionUserId) {
        StringBuffer sb = new StringBuffer();
        Map<String,Object> paramMap = new HashMap<String, Object>();
        sb.append(" select count(*) from UserCollection where 1=1 ");
        sb.append(" AND collectionUserId =:collectionUserId");
        sb.append(" AND attentionUserId = :attentionUserId");
        paramMap.put("collectionUserId",userId);
        paramMap.put("attentionUserId",attentionUserId);

        return daoUtils.getTotalByHQL(sb.toString(),paramMap);
    }

    @Override
    public UserCollection getUserCollectionByCollectionUserIdAndAttentionUserId(String userId, String attentionUserId) {
        return repository.getUserCollectionByCollectionUserIdAndAttentionUserId(userId,attentionUserId);
    }

    @Override
    public int queryUserCollectionFansByQue(String userId) {
        StringBuffer sb = new StringBuffer();
        Map<String,Object> paramMap = new HashMap<String, Object>();
        sb.append(" select count(*) from UserCollection where 1=1 ");
        sb.append(" AND attentionUserId = :attentionUserId");
        paramMap.put("attentionUserId",userId);

        return daoUtils.getTotalByHQL(sb.toString(),paramMap);
    }

    @Override
    public List<com.memory.entity.model.UserCollection> queryUserFansListByQue(int pageIndex, int pageLimit, String collection_user_id) {
        StringBuffer sb = new StringBuffer();
        DaoUtils.Page page = daoUtils.getPage(pageIndex, pageLimit);
        Map<String,Object> paramMap = new HashMap<String, Object>();
        /**
         *  this.id = id;
         *         this.collectionUserId = collectionUserId;
         *         this.attentionUserId = attentionUserId;
         *         this.isFollow = isFollow;
         *         this.createTime = createTime;
         *         this.userName = userName;
         *         this.userLogo = userLogo;
         *         this.userId = userId;
         */
        sb.append(" select new com.memory.entity.model.UserCollection(uc.id,uc.collectionUserId,uc.attentionUserId,uc.isFollow," +
                "uc.createTime,u.userName,u.userLogo,u.id) ");
        sb.append(" from UserCollection uc ,User u where uc.attentionUserId = u.id ");
        sb.append(" AND uc.attentionUserId =:attentionUserId");
        sb.append(" AND uc.isFollow = 1");
        sb.append(" ORDER by uc.createTime desc");
        paramMap.put("attentionUserId",collection_user_id);
        return  daoUtils.findByHQL(sb.toString(), paramMap, null);
    }


}
