package com.memory.xhm.service;

import com.memory.xhm.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 13:46
 * @Description:
 */
public interface ArticleService {
    //查询全部文章
    Map<String, Object> sel(Integer start, Integer limit);
    //查询自己的文章
    Map<String, Object> selByUserId(String userId, Integer start, Integer limit);
    //添加文章
    Article add(Article article);
}
