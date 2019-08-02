package com.memory.app.service;

import com.memory.entity.ArticleCommentLike;

/**
 * @author INS6+
 * @date 2019/8/2 15:19
 */

public interface ArticleCommentLikeService {
    ArticleCommentLike add(ArticleCommentLike articleCommentLike);

    ArticleCommentLike update(ArticleCommentLike articleCommentLike);

    int getArticleCommentLikeCount(String userId, String commentId);

    ArticleCommentLike getArticleCommentLikeByUserIdAndCommentId(String userId, String commentId);
}
