package com.memory.app.service;

import com.memory.entity.Article;
import com.memory.entity.ArticleLike;

import java.util.Map;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 13:46
 * @Description:
 */
public interface ArticleService {
    //查询全部文章
    Map<String, Object> sel(String typeId, Integer start, Integer limit);
    //查询文章详情
    Article selById(String aid);
    //查询自己的文章
    Map<String, Object> selByUserId(String userId, Integer start, Integer limit);
    //添加文章
    Article add(Article article);
    //删除文章
    Article del(String aid, String userId);
    //文章点赞
    ArticleLike like(String aid, String userId);

    Article getArticleById(String id);
}
