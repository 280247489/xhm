package com.memory.app.service.impl;

import com.memory.common.utils.Utils;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.Article;
import com.memory.entity.ArticleLike;
import com.memory.entity.User;
import com.memory.app.repository.ArticleRepository;
import com.memory.app.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 13:46
 * @Description:
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private DaoUtils daoUtils;

    @Autowired
    private ArticleRepository articleRepository;


    @Override
    public Map<String, Object> sel(String typeId, Integer start, Integer limit) {
        Map<String, Object> reutnrMap = new HashMap<>();
        StringBuffer sb =new StringBuffer(" from Article where articleCheckYn = 1 and articleDelYn = 0 and articleOnline = 1");
        StringBuffer sb_count =new StringBuffer("select count(*) from Article where articleCheckYn = 1 and articleDelYn = 0 and articleOnline = 1");
        Map<String, Object> map = null;
        if(!"".equals(typeId)){
            map = new HashMap<>();
            map.put("typeId", typeId);
            sb.append(" and typeId=:typeId");
            sb_count.append(" and typeId=:typeId");
        }
        sb.append(" order by articleCreateTime desc");
        DaoUtils.Page page = daoUtils.getPage(start, limit);
        reutnrMap.put("list", daoUtils.findByHQL(sb.toString(), map, page));
        reutnrMap.put("count", daoUtils.getTotalByHQL(sb_count.toString(), map));
        reutnrMap.put("start", page.getPageIndex());
        reutnrMap.put("limit", page.getLimit());
        return reutnrMap;
    }

    @Transactional
    @Override
    public Article selById(String aid) {
        Article article = (Article) daoUtils.getById("Article", aid);
        if(article != null && article.getArticleCheckYn() == 1 && article.getArticleDelYn() == 0){
            article.setArticleTotalView(article.getArticleTotalView() + 1);
        }
        return article;
    }

    @Override
    public Map<String, Object> selByUserId(String userId, Integer start, Integer limit) {
        Map<String, Object> reutnrMap = new HashMap<>();
        StringBuffer sb =new StringBuffer(" from Article where articleCreateUserId=:userId And articleDelYn = 0  order by articleCheckTime desc");
        StringBuffer sb_count =new StringBuffer("select count(*) from Article where articleCreateUserId=:userId And articleDelYn = 0 ");
        DaoUtils.Page page = daoUtils.getPage(start, limit);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        reutnrMap.put("list", daoUtils.findByHQL(sb.toString(), map, page));
        reutnrMap.put("count", daoUtils.getTotalByHQL(sb_count.toString(), map));
        reutnrMap.put("start", page.getPageIndex());
        reutnrMap.put("limit", page.getLimit());

        return reutnrMap;
    }

    @Transactional
    @Override
    public Article add(Article article) {
        daoUtils.save(article);
        //articleRepository.save(article);
        return article;
    }

    @Transactional
    @Override
    public Article del(String aid, String userId) {
        StringBuffer stringBuffer = new StringBuffer(" from Article where id=:aid ");
        Map<String, Object> map = new HashMap<>();
        map.put("aid", aid);
        Article article = (Article) daoUtils.findObjectHQL(stringBuffer.toString(), map);
        if(article != null && article.getArticleCreateUserId().equals(userId) && article.getArticleDelYn() == 0){
            article.setArticleDelYn(1);
        }else{
            article = null;
        }
        return article;
    }

    @Transactional
    @Override
    public ArticleLike like(String aid, String uid) {
        Article article = (Article) daoUtils.getById("Article", aid);
        User user = (User) daoUtils.getById("User", uid);
        ArticleLike articleLike = null;
        if(article != null && user != null){
            articleLike = this.getByAidUid(aid, uid);
            if(articleLike != null){
                if(articleLike.getLikeStatus()==1){
                    articleLike.setLikeStatus(0);
                    article.setArticleTotalLike(article.getArticleTotalLike()-1);
                }else{
                    articleLike.setLikeStatus(1);
                    article.setArticleTotalLike(article.getArticleTotalLike()+1);
                }
            }else{
                if(article !=null && user != null){
                    articleLike = new ArticleLike();
                    articleLike.setId(Utils.getShortUUID());
                    articleLike.setArticleId(aid);
                    articleLike.setUserId(uid);
                    articleLike.setLikeStatus(1);
                    articleLike.setCreateTime(new Date());

                    article.setArticleTotalLike(article.getArticleTotalLike()+1);
                }
            }
            daoUtils.save(article);
            daoUtils.save(articleLike);
        }
        return articleLike;
    }

    private ArticleLike getByAidUid(String aid, String uid){
        StringBuffer sb = new StringBuffer(" from ArticleLike where articleId=:aid and userId=:uid ");
        Map<String, Object> map = new HashMap<>();
        map.put("aid", aid);
        map.put("uid", uid);
        ArticleLike articleLike = (ArticleLike) daoUtils.findObjectHQL(sb.toString(), map);
        return articleLike;
    }
}
