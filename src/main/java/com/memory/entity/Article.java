package com.memory.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * @author INS6+
 * @date 2019/7/6 14:18
 */

@Entity
public class Article {
    private String id;
    private String typeId;
    private String articleTitle;
    private String articleLogo;
    private String articlePicture;
    private String articleContent;
    private String articleTopicsId;
    private String articleTopics;
    private String articleLabel;
    private String articleKeyWords;
    private int articleOnline;
    private int articleTotalView;
    private int articleTotalShare;
    private int articleTotalLike;
    private Date articleCreateTime;
    private String articleCreateUserId;
    private int articleCheckYn;
    private Date articleCheckTime;
    private String articleCheckAdminId;
    private int articleDelYn;
    private int articleTopYn;
    private String articleCreateUserName;
    private int articleTotalComment;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type_id")
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "article_title")
    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    @Basic
    @Column(name = "article_logo")
    public String getArticleLogo() {
        return articleLogo;
    }

    public void setArticleLogo(String articleLogo) {
        this.articleLogo = articleLogo;
    }

    @Basic
    @Column(name = "article_picture")
    public String getArticlePicture() {
        return articlePicture;
    }

    public void setArticlePicture(String articlePicture) {
        this.articlePicture = articlePicture;
    }

    @Basic
    @Column(name = "article_content")
    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @Basic
    @Column(name = "article_topics_id")
    public String getArticleTopicsId() {
        return articleTopicsId;
    }

    public void setArticleTopicsId(String articleTopicsId) {
        this.articleTopicsId = articleTopicsId;
    }

    @Basic
    @Column(name = "article_topics")
    public String getArticleTopics() {
        return articleTopics;
    }

    public void setArticleTopics(String articleTopics) {
        this.articleTopics = articleTopics;
    }

    @Basic
    @Column(name = "article_label")
    public String getArticleLabel() {
        return articleLabel;
    }

    public void setArticleLabel(String articleLabel) {
        this.articleLabel = articleLabel;
    }

    @Basic
    @Column(name = "article_key_words")
    public String getArticleKeyWords() {
        return articleKeyWords;
    }

    public void setArticleKeyWords(String articleKeyWords) {
        this.articleKeyWords = articleKeyWords;
    }

    @Basic
    @Column(name = "article_online")
    public int getArticleOnline() {
        return articleOnline;
    }

    public void setArticleOnline(int articleOnline) {
        this.articleOnline = articleOnline;
    }

    @Basic
    @Column(name = "article_total_view")
    public int getArticleTotalView() {
        return articleTotalView;
    }

    public void setArticleTotalView(int articleTotalView) {
        this.articleTotalView = articleTotalView;
    }

    @Basic
    @Column(name = "article_total_share")
    public int getArticleTotalShare() {
        return articleTotalShare;
    }

    public void setArticleTotalShare(int articleTotalShare) {
        this.articleTotalShare = articleTotalShare;
    }

    @Basic
    @Column(name = "article_total_like")
    public int getArticleTotalLike() {
        return articleTotalLike;
    }

    public void setArticleTotalLike(int articleTotalLike) {
        this.articleTotalLike = articleTotalLike;
    }

    @Basic
    @Column(name = "article_create_time")
    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    @Basic
    @Column(name = "article_create_user_id")
    public String getArticleCreateUserId() {
        return articleCreateUserId;
    }

    public void setArticleCreateUserId(String articleCreateUserId) {
        this.articleCreateUserId = articleCreateUserId;
    }

    @Basic
    @Column(name = "article_check_yn")
    public int getArticleCheckYn() {
        return articleCheckYn;
    }

    public void setArticleCheckYn(int articleCheckYn) {
        this.articleCheckYn = articleCheckYn;
    }

    @Basic
    @Column(name = "article_check_time")
    public Date getArticleCheckTime() {
        return articleCheckTime;
    }

    public void setArticleCheckTime(Date articleCheckTime) {
        this.articleCheckTime = articleCheckTime;
    }

    @Basic
    @Column(name = "article_check_admin_id")
    public String getArticleCheckAdminId() {
        return articleCheckAdminId;
    }

    public void setArticleCheckAdminId(String articleCheckAdminId) {
        this.articleCheckAdminId = articleCheckAdminId;
    }

    @Basic
    @Column(name = "article_del_yn")
    public int getArticleDelYn() {
        return articleDelYn;
    }

    public void setArticleDelYn(int articleDelYn) {
        this.articleDelYn = articleDelYn;
    }

    @Basic
    @Column(name = "article_top_yn")
    public int getArticleTopYn() {
        return articleTopYn;
    }

    public void setArticleTopYn(int articleTopYn) {
        this.articleTopYn = articleTopYn;
    }

    public String getArticleCreateUserName() {
        return articleCreateUserName;
    }

    public void setArticleCreateUserName(String articleCreateUserName) {
        this.articleCreateUserName = articleCreateUserName;
    }
    @Basic
    @Column(name = "article_total_comment")
    public int getArticleTotalComment() {
        return articleTotalComment;
    }

    public void setArticleTotalComment(int articleTotalComment) {
        this.articleTotalComment = articleTotalComment;
    }

    public Article() {
    }

    public Article(String id, String typeId, String articleTitle, String articleLogo, String articlePicture, String articleContent, String articleTopicsId, String articleTopics, String articleLabel, String articleKeyWords, int articleOnline, int articleTotalView, int articleTotalShare, int articleTotalLike, Date articleCreateTime, String articleCreateUserId, int articleCheckYn, Date articleCheckTime, String articleCheckAdminId, int articleDelYn, int articleTopYn) {
        this.id = id;
        this.typeId = typeId;
        this.articleTitle = articleTitle;
        this.articleLogo = articleLogo;
        this.articlePicture = articlePicture;
        this.articleContent = articleContent;
        this.articleTopicsId = articleTopicsId;
        this.articleTopics = articleTopics;
        this.articleLabel = articleLabel;
        this.articleKeyWords = articleKeyWords;
        this.articleOnline = articleOnline;
        this.articleTotalView = articleTotalView;
        this.articleTotalShare = articleTotalShare;
        this.articleTotalLike = articleTotalLike;
        this.articleCreateTime = articleCreateTime;
        this.articleCreateUserId = articleCreateUserId;
        this.articleCheckYn = articleCheckYn;
        this.articleCheckTime = articleCheckTime;
        this.articleCheckAdminId = articleCheckAdminId;
        this.articleDelYn = articleDelYn;
        this.articleTopYn = articleTopYn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return articleOnline == article.articleOnline &&
                articleTotalView == article.articleTotalView &&
                articleTotalShare == article.articleTotalShare &&
                articleTotalLike == article.articleTotalLike &&
                articleCheckYn == article.articleCheckYn &&
                articleDelYn == article.articleDelYn &&
                articleTopYn == article.articleTopYn &&
                Objects.equals(id, article.id) &&
                Objects.equals(typeId, article.typeId) &&
                Objects.equals(articleTitle, article.articleTitle) &&
                Objects.equals(articleLogo, article.articleLogo) &&
                Objects.equals(articlePicture, article.articlePicture) &&
                Objects.equals(articleContent, article.articleContent) &&
                Objects.equals(articleTopicsId, article.articleTopicsId) &&
                Objects.equals(articleTopics, article.articleTopics) &&
                Objects.equals(articleLabel, article.articleLabel) &&
                Objects.equals(articleKeyWords, article.articleKeyWords) &&
                Objects.equals(articleCreateTime, article.articleCreateTime) &&
                Objects.equals(articleCreateUserId, article.articleCreateUserId) &&
                Objects.equals(articleCheckTime, article.articleCheckTime) &&
                Objects.equals(articleCheckAdminId, article.articleCheckAdminId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeId, articleTitle, articleLogo, articlePicture, articleContent, articleTopicsId, articleTopics, articleLabel, articleKeyWords, articleOnline, articleTotalView, articleTotalShare, articleTotalLike, articleCreateTime, articleCreateUserId, articleCheckYn, articleCheckTime, articleCheckAdminId, articleDelYn, articleTopYn);
    }
}
