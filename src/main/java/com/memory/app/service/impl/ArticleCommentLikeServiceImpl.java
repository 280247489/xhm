package com.memory.app.service.impl;

import com.memory.app.repository.ArticleCommentLikeRepository;
import com.memory.app.service.ArticleCommentLikeService;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.ArticleCommentLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author INS6+
 * @date 2019/8/2 15:19
 */
@Service
public class ArticleCommentLikeServiceImpl implements ArticleCommentLikeService {

    @Autowired
    private ArticleCommentLikeRepository repository;

    @Autowired
    private DaoUtils daoUtils;

    @Override
   public ArticleCommentLike add(ArticleCommentLike articleCommentLike){
       return repository.save(articleCommentLike);
    }

    @Override
    public ArticleCommentLike update(ArticleCommentLike articleCommentLike){
        return repository.save(articleCommentLike);
    }


    /**
     * 根据userId 和commentId 查询这条评论有多少个赞
     * @param commentId
     * @return
     */
    @Override
    public int getArticleCommentLikeCount(String commentId){
        StringBuffer sb = new StringBuffer();
        sb.append("select count(*) From ArticleCommentLike where commentId=:commentId and isLike=0");
        Map<String,Object> map = new HashMap<>();
        map.put("commentId",commentId);

        return  daoUtils.getTotalByHQL(sb.toString(), map);
    }

    /**
     * 根据 userId 和 commentId 查询
     * @param userId
     * @param commentId
     * @return
     */
    @Override
    public ArticleCommentLike getArticleCommentLikeByUserIdAndCommentId(String userId, String commentId){

        return repository.getArticleCommentLikeByUserIdAndCommentId(userId, commentId);
    }






}
