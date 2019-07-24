package com.memory.app.service;

import com.memory.entity.ArticleLike;

import java.util.List;

/**
 * @author INS6+
 * @date 2019/7/24 19:19
 */

public interface ArticleLikeService {

    ArticleLike addArticleLike(ArticleLike articleLike);

    ArticleLike updateArticleLike(ArticleLike articleLike);

    ArticleLike getArticleLike(String userId, String articleId);

    List<com.memory.entity.model.ArticleLike> queryArticleLikeByQue(Integer pageIndex, Integer limit, String userId);
}
