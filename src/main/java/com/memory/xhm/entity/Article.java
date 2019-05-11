package com.memory.xhm.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 10:20
 * @Description:
 */
@Entity
public class Article {
    private int id;
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
    private Timestamp articleCreateTime;
    private String articleCreateUserId;
    private int articleCheckYn;
    private Timestamp articleCheckTime;
    private String articleCheckAdminId;
    private int articleDelYn;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public Timestamp getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Timestamp articleCreateTime) {
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
    public Timestamp getArticleCheckTime() {
        return articleCheckTime;
    }

    public void setArticleCheckTime(Timestamp articleCheckTime) {
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
        return id == article.id &&
                articleOnline == article.articleOnline &&
                articleTotalView == article.articleTotalView &&
                articleTotalShare == article.articleTotalShare &&
                articleTotalLike == article.articleTotalLike &&
                articleCheckYn == article.articleCheckYn &&
                articleDelYn == article.articleDelYn &&
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
}
