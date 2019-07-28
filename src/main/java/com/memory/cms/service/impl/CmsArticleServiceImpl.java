package com.memory.cms.service.impl;

import com.memory.cms.service.CmsArticleService;
import com.memory.common.utils.Utils;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 13:46
 * @Description:
 */
@Service
public class CmsArticleServiceImpl implements CmsArticleService {

    @Autowired
    private DaoUtils daoUtils;

    @Override
    public Map<String, Object> sel(String userName, String typeId, Integer online, Integer check, Integer del, String startTime, String endTime, Integer start, Integer limit) {
        Map<String, Object> reutnrMap = new HashMap<>();
        /**
         *    this.id = id;
         *         this.typeId = typeId;
         *         this.articleTitle = articleTitle;
         *         this.articleLogo = articleLogo;
         *         this.articlePicture = articlePicture;
         *         this.articleContent = articleContent;
         *         this.articleTopicsId = articleTopicsId;
         *         this.articleTopics = articleTopics;
         *         this.articleLabel = articleLabel;
         *         this.articleKeyWords = articleKeyWords;
         *         this.articleOnline = articleOnline;
         *         this.articleTotalView = articleTotalView;
         *         this.articleTotalShare = articleTotalShare;
         *         this.articleTotalLike = articleTotalLike;
         *         this.articleCreateTime = articleCreateTime;
         *         this.articleCreateUserId = articleCreateUserId;
         *         this.articleCheckYn = articleCheckYn;
         *         this.articleCheckTime = articleCheckTime;
         *         this.articleCheckAdminId = articleCheckAdminId;
         *         this.articleDelYn = articleDelYn;
         *         this.articleTopYn = articleTopYn;
         */

        StringBuffer sb =new StringBuffer("select new Article(a.id, a.typeId, " +
                "a.articleTitle, a.articleLogo, " +
                "a.articlePicture, a.articleContent, " +
                "a.articleTopicsId, a.articleTopics, " +
                "a.articleLabel, a.articleKeyWords, " +
                "a.articleOnline, a.articleTotalView, " +
                "a.articleTotalShare, a.articleTotalLike, " +
                "a.articleCreateTime, u.userName, " +
                "a.articleCheckYn, a.articleCheckTime, " +
                "a.articleCheckAdminId, a.articleDelYn , a.articleTopYn) " +
                "from Article a, User u where a.articleCreateUserId = u.id ");
        StringBuffer sb_count =new StringBuffer("select count(*) from Article a, User u where a.articleCreateUserId = u.id ");
        Map<String, Object> map = new HashMap<>();
        if(!"".equals(userName)){
            map.put("userName", "%"+userName+"%");
            sb.append(" and u.userName like :userName");
            sb_count.append(" and u.userName like :userName");
        }
        if(!"".equals(typeId)){
            map.put("typeId", typeId);
            sb.append(" and a.typeId=:typeId");
            sb_count.append(" and a.typeId=:typeId");
        }
        if(online != null){
            map.put("online", online);
            sb.append(" and a.articleOnline=:online");
            sb_count.append(" and a.articleOnline=:online");
        }
        if(check != null){
            map.put("check", check);
            sb.append(" and a.articleCheckYn=:check");
            sb_count.append(" and a.articleCheckYn=:check");
        }
        if(del != null){
            map.put("del", del);
            sb.append(" and a.articleDelYn=:del");
            sb_count.append(" and a.articleDelYn=:del");
        }
        if(!"".equals(startTime)){
            try {
                Date sTime = Utils.sf_yMd.parse(startTime);
                map.put("sTime", sTime);
                sb.append(" and a.articleCreateTime >= :sTime");
                sb_count.append(" and a.articleCreateTime >= :sTime");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(!"".equals(endTime)){
            try {

                Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
                c.setTime(Utils.sf_yMd.parse(endTime));
                c.add(Calendar.DATE, 1);
                map.put("eTime", c.getTime());
                sb.append(" and a.articleCreateTime <= :eTime");
                sb_count.append(" and a.articleCreateTime <= :eTime");
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        sb.append(" order by a.articleCreateTime desc");
        DaoUtils.Page page = daoUtils.getPage(start, limit);
        reutnrMap.put("list", daoUtils.findByHQL(sb.toString(), map.size()>0?map:null, page));
        reutnrMap.put("count", daoUtils.getTotalByHQL(sb_count.toString(), map));
        reutnrMap.put("start", page.getPageIndex());
        reutnrMap.put("limit", page.getLimit());
        return reutnrMap;
    }

    @Override
    public Map<String, Object> selCheck(String userName, String typeId, String startTime, String endTime, Integer start, Integer limit) {
        Map<String, Object> reutnrMap = new HashMap<>();
        StringBuffer sb =new StringBuffer("select new Article(a.id, a.typeId, " +
                "a.articleTitle, a.articleLogo, " +
                "a.articlePicture, a.articleContent, " +
                "a.articleTopicsId, a.articleTopics, " +
                "a.articleLabel, a.articleKeyWords, " +
                "a.articleOnline, a.articleTotalView, " +
                "a.articleTotalShare, a.articleTotalLike, " +
                "a.articleCreateTime, u.userName, " +
                "a.articleCheckYn, a.articleCheckTime, " +
                "a.articleCheckAdminId, a.articleDelYn,a.articleTopYn) " +
                "from Article a, User u where a.articleCreateUserId = u.id and a.articleCheckYn=0 and a.articleDelYn=0 ");
        StringBuffer sb_count =new StringBuffer("select count(*) from Article a, User u where a.articleCreateUserId = u.id and a.articleCheckYn=0 and a.articleDelYn=0 ");
        Map<String, Object> map = new HashMap<>();
        if(!"".equals(userName)){
            map.put("userName", "%"+userName+"%");
            sb.append(" and u.userName like :userName");
            sb_count.append(" and u.userName like :userName");
        }
        if(!"".equals(typeId)){
            map.put("typeId", typeId);
            sb.append(" and a.typeId=:typeId");
            sb_count.append(" and a.typeId=:typeId");
        }
        if(!"".equals(startTime)){
            try {
                Date sTime = Utils.sf_yMd.parse(startTime);
                map.put("sTime", sTime);
                sb.append(" and a.articleCreateTime >= :sTime");
                sb_count.append(" and a.articleCreateTime >= :sTime");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(!"".equals(endTime)){
            try {

                Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
                c.setTime(Utils.sf_yMd.parse(endTime));
                c.add(Calendar.DATE, 1);
                map.put("eTime", c.getTime());
                sb.append(" and a.articleCreateTime <= :eTime");
                sb_count.append(" and a.articleCreateTime <= :eTime");
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        sb.append(" order by a.articleCreateTime");
        DaoUtils.Page page = daoUtils.getPage(start, limit);
        reutnrMap.put("list", daoUtils.findByHQL(sb.toString(), map.size()>0?map:null, page));
        reutnrMap.put("count", daoUtils.getTotalByHQL(sb_count.toString(), map));
        reutnrMap.put("start", page.getPageIndex());
        reutnrMap.put("limit", page.getLimit());
        return reutnrMap;
    }

    @Transactional
    @Override
    public Article selById(String aid) {
        Article article = (Article) daoUtils.getById("Article", aid);
        return article;
    }

    @Transactional
    @Override
    public Article check(String aid, Integer checkYn) {
        Article article = (Article) daoUtils.getById("Article", aid);
        if(article != null){
            if(article.getArticleCheckYn() == 0){
                article.setArticleCheckYn(checkYn==1?1:2);
                if(checkYn==1){
                    article.setArticleOnline(1);
                    article.setArticleCheckTime(new Date());
                }
            }
        }
        return article;
    }

    @Transactional
    @Override
    public Article isTop(String aid) {
        Article article = (Article) daoUtils.getById("Article", aid);
        if(article != null){
            //原来被取消置顶了
            if(article.getArticleTopYn() == 0){
                article.setArticleTopYn(1);
                article.setArticleOnline(1);
                article.setArticleCheckYn(1);
                article.setArticleCheckTime(new Date());
            }else{
                article.setArticleTopYn(0);
            }

        }
        return article;
    }

    @Transactional
    @Override
    public Article online(String aid) {
        Article article = (Article) daoUtils.getById("Article", aid);
        if(article != null){
            if(article.getArticleOnline()==0){
                article.setArticleOnline(1);
            }else{
                article.setArticleOnline(0);
            }
        }
        return article;
    }

    @Transactional
    @Override
    public Article del(String aid) {
        Article article = (Article) daoUtils.getById("Article", aid);
        if(article != null){
            if(article.getArticleDelYn() == 0) {
                article.setArticleDelYn(1);
                article.setArticleOnline(0);
            }
        }
        return article;
    }

    @Transactional
    @Override
    public Article top(String aid) {
        Article article = (Article) daoUtils.getById("Article", aid);
        if(article != null){
            if(article.getArticleTopYn()==0){
                article.setArticleTopYn(1);
            }else{
                article.setArticleTopYn(0);
            }
        }
        return article;
    }
}
