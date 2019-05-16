package com.memory.entity.model;

import com.memory.entity.Article;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/12 0012 14:05
 * @Description:
 */
public class ArticleModel {
    private Article article;
    private List<MultipartFile> pictures;

    public List<MultipartFile> getPictures() {
        return pictures;
    }

    public void setPictures(List<MultipartFile> pictures) {
        this.pictures = pictures;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
