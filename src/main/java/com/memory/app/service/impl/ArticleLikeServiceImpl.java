package com.memory.app.service.impl;

import com.memory.app.repository.ArticleLikeRepository;
import com.memory.app.service.ArticleLikeService;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.Article;
import com.memory.entity.ArticleLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author INS6+
 * @date 2019/7/24 19:19
 */

@Service
public class ArticleLikeServiceImpl implements ArticleLikeService {

    @Autowired
    private DaoUtils daoUtils;

    @Autowired
    private ArticleLikeRepository repository;

    @Override
    public ArticleLike addArticleLike(ArticleLike articleLike){
        return repository.save(articleLike);
    }

    @Override
    public ArticleLike updateArticleLike(ArticleLike articleLike){
        return repository.save(articleLike);
    }

    @Override
    public ArticleLike getArticleLike(String userId, String articleId){
        return repository.getArticleLikeByUserIdAndArticleId(userId, articleId);
    }

    @Override
    public List< com.memory.entity.model.ArticleLike> queryArticleLikeByQue(Integer pageIndex, Integer limit, String userId){
        StringBuffer sb = new StringBuffer();
        /**
         this.id = id;
         this.userId = userId;
         this.articleId = articleId;
         this.likeStatus = likeStatus;
         this.createTime = createTime;
         this.typeId = typeId;
         this.articleTitle = articleTitle;
         this.articleLogo = articleLogo;
         this.articleTopicsId = articleTopicsId;
         this.articleTopics = articleTopics;
         this.articleTotalView = articleTotalView;
         this.articleTotalShare = articleTotalShare;
         this.articleTotalLike = articleTotalLike;
         this.articleCreateUserId = articleCreateUserId;
         this.articleCreateUserName = articleCreateUserName;
         this.articleTotalComment = articleTotalComment;
         this.articleCreateUserLogo = articleCreateUserLogo;
         */
        DaoUtils.Page page = daoUtils.getPage(pageIndex, limit);
        sb.append("select new  com.memory.entity.model.ArticleLike (al.id,al.userId,al.articleId,al.likeStatus," +
                "al.createTime,a.typeId,a.articleTitle,a.articleLogo," +
                "a.articleTopicsId,a.articleTopics,a.articleTotalView,a.articleTotalShare," +
                "a.articleTotalLike,a.articleCreateUserId,a.articleCreateUserName,a.articleTotalComment,a.articleCreateUserLogo)  ");

        //sb.append(" from  ArticleLike al  left join  Article a  on al.articleId = a.id where 1=1");
        sb.append(" from  Article a   left join   ArticleLike al on al.articleId = a.id where 1=1");
       // sb.append(" from ArticleLike al, Article a where al.articleId = a.id  ");

        sb.append(" AND al.userId = :userId");
        sb.append(" AND al.likeStatus = 1");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userId",userId);
        sb.append(" ORDER BY al.createTime desc");


        System.out.println("hql ==" + sb.toString());
        return daoUtils.findByHQL(sb.toString(), map, page);
    }










}
