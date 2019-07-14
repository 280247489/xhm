package com.memory.app.repository;

import com.memory.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 13:45
 * @Description:
 */
public interface ArticleRepository extends JpaRepository<Article, String> {
}
