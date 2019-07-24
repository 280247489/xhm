package com.memory.app.repository;

import com.memory.entity.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author INS6+
 * @date 2019/7/24 19:18
 */

public interface ArticleLikeRepository extends JpaRepository<ArticleLike,String > {

    ArticleLike getArticleLikeByUserIdAndArticleId(String userId,String articleId);

}
