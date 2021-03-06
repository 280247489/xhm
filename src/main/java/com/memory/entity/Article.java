package com.memory.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/12 0012 16:13
 * @Description:
 */
@Entity
public class Article {
    private String id;
    private String typeId;
    private String articleTitle;
    private String articleLogo;
    private String articlePicture;
    private String articleContent;
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

    @Id
    @Column(name = "id", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type_id", nullable = false, length = 255)
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "article_title", nullable = false, length = 255)
    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    @Basic
    @Column(name = "article_logo", nullable = false, length = 255)
    public String getArticleLogo() {
        return articleLogo;
    }

    public void setArticleLogo(String articleLogo) {
        this.articleLogo = articleLogo;
    }

    @Basic
    @Column(name = "article_picture", nullable = false, length = -1)
    public String getArticlePicture() {
        return articlePicture;
    }

    public void setArticlePicture(String articlePicture) {
        this.articlePicture = articlePicture;
    }

    @Basic
    @Column(name = "article_content", nullable = false, length = -1)
    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @Basic
    @Column(name = "article_label", nullable = false, length = 255)
    public String getArticleLabel() {
        return articleLabel;
    }

    public void setArticleLabel(String articleLabel) {
        this.articleLabel = articleLabel;
    }

    @Basic
    @Column(name = "article_key_words", nullable = false, length = -1)
    public String getArticleKeyWords() {
        return articleKeyWords;
    }

    public void setArticleKeyWords(String articleKeyWords) {
        this.articleKeyWords = articleKeyWords;
    }

    @Basic
    @Column(name = "article_online", nullable = false)
    public int getArticleOnline() {
        return articleOnline;
    }

    public void setArticleOnline(int articleOnline) {
        this.articleOnline = articleOnline;
    }

    @Basic
    @Column(name = "article_total_view", nullable = false)
    public int getArticleTotalView() {
        return articleTotalView;
    }

    public void setArticleTotalView(int articleTotalView) {
        this.articleTotalView = articleTotalView;
    }

    @Basic
    @Column(name = "article_total_share", nullable = false)
    public int getArticleTotalShare() {
        return articleTotalShare;
    }

    public void setArticleTotalShare(int articleTotalShare) {
        this.articleTotalShare = articleTotalShare;
    }

    @Basic
    @Column(name = "article_total_like", nullable = false)
    public int getArticleTotalLike() {
        return articleTotalLike;
    }

    public void setArticleTotalLike(int articleTotalLike) {
        this.articleTotalLike = articleTotalLike;
    }

    @Basic
    @Column(name = "article_create_time", nullable = false)
    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    @Basic
    @Column(name = "article_create_user_id", nullable = false, length = 255)
    public String getArticleCreateUserId() {
        return articleCreateUserId;
    }

    public void setArticleCreateUserId(String articleCreateUserId) {
        this.articleCreateUserId = articleCreateUserId;
    }

    @Basic
    @Column(name = "article_check_yn", nullable = false)
    public int getArticleCheckYn() {
        return articleCheckYn;
    }

    public void setArticleCheckYn(int articleCheckYn) {
        this.articleCheckYn = articleCheckYn;
    }

    @Basic
    @Column(name = "article_check_time", nullable = false)
    public Date getArticleCheckTime() {
        return articleCheckTime;
    }

    public void setArticleCheckTime(Date articleCheckTime) {
        this.articleCheckTime = articleCheckTime;
    }

    @Basic
    @Column(name = "article_check_admin_id", nullable = false, length = 255)
    public String getArticleCheckAdminId() {
        return articleCheckAdminId;
    }

    public void setArticleCheckAdminId(String articleCheckAdminId) {
        this.articleCheckAdminId = articleCheckAdminId;
    }

    @Basic
    @Column(name = "article_del_yn", nullable = false)
    public int getArticleDelYn() {
        return articleDelYn;
    }

    public void setArticleDelYn(int articleDelYn) {
        this.articleDelYn = articleDelYn;
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
                Objects.equals(id, article.id) &&
                Objects.equals(typeId, article.typeId) &&
                Objects.equals(articleTitle, article.articleTitle) &&
                Objects.equals(articleLogo, article.articleLogo) &&
                Objects.equals(articlePicture, article.articlePicture) &&
                Objects.equals(articleContent, article.articleContent) &&
                Objects.equals(articleLabel, article.articleLabel) &&
                Objects.equals(articleKeyWords, article.articleKeyWords) &&
                Objects.equals(articleCreateTime, article.articleCreateTime) &&
                Objects.equals(articleCreateUserId, article.articleCreateUserId) &&
                Objects.equals(articleCheckTime, article.articleCheckTime) &&
                Objects.equals(articleCheckAdminId, article.articleCheckAdminId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeId, articleTitle, articleLogo, articlePicture, articleContent, articleLabel, articleKeyWords, articleOnline, articleTotalView, articleTotalShare, articleTotalLike, articleCreateTime, articleCreateUserId, articleCheckYn, articleCheckTime, articleCheckAdminId, articleDelYn);
    }

    public Article() {
    }

    public Article(String id, String typeId, String articleTitle, String articleLogo, String articlePicture, String articleContent, String articleLabel, String articleKeyWords, int articleOnline, int articleTotalView, int articleTotalShare, int articleTotalLike, Date articleCreateTime, String articleCreateUserId, int articleCheckYn, Date articleCheckTime, String articleCheckAdminId, int articleDelYn) {
        this.id = id;
        this.typeId = typeId;
        this.articleTitle = articleTitle;
        this.articleLogo = articleLogo;
        this.articlePicture = articlePicture;
        this.articleContent = articleContent;
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
    }
}
