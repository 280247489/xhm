package com.memory.cms.service;

import com.memory.entity.Article;

import java.util.Date;
import java.util.Map;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 13:46
 * @Description:
 */
public interface CmsArticleService {
    //查询全部文章
    Map<String, Object> sel(String userName, String typeId, Integer online, Integer check, Integer del, String startTime, String endTime, Integer start, Integer limit);

    //查询未审核文章全部文章
    Map<String, Object> selCheck(String userName, String typeId, String startTime, String endTime, Integer start, Integer limit);

    //查询文章详情
    Article selById(String aid);
    //审核文章
    Article check(String aid, Integer checkYn);
    //上下架文章
    Article online(String aid);
    //删除文章
    Article del(String aid);
}
