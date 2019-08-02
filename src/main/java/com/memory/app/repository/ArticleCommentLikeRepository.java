package com.memory.app.repository;

import com.memory.entity.ArticleCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author INS6+
 * @date 2019/8/2 15:17
 */

public interface ArticleCommentLikeRepository extends JpaRepository<ArticleCommentLike,String> {


    ArticleCommentLike getArticleCommentLikeByUserIdAndCommentId(String userId, String commentId);

    int countByCommentIdAndUserId(String commentId,String  userId);





}
