package com.memory.entity.model;

import java.util.Date;

/**
 * @author INS6+
 * @date 2019/7/24 19:50
 */

public class ArticleLike {

    private String id;
    private String userId;
    private String articleId;
    private int likeStatus;
    private Date createTime;
    private String typeId;
    private String articleTitle;
    private String articleLogo;
    private String articleTopicsId;
    private String articleTopics;
    private int articleTotalView;
    private int articleTotalShare;
    private int articleTotalLike;
    private String articleCreateUserId;
    private String articleCreateUserName;
    private int articleTotalComment;
    private String articleCreateUserLogo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public int getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(int likeStatus) {
        this.likeStatus = likeStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleLogo() {
        return articleLogo;
    }

    public void setArticleLogo(String articleLogo) {
        this.articleLogo = articleLogo;
    }

    public String getArticleTopicsId() {
        return articleTopicsId;
    }

    public void setArticleTopicsId(String articleTopicsId) {
        this.articleTopicsId = articleTopicsId;
    }

    public String getArticleTopics() {
        return articleTopics;
    }

    public void setArticleTopics(String articleTopics) {
        this.articleTopics = articleTopics;
    }

    public int getArticleTotalView() {
        return articleTotalView;
    }

    public void setArticleTotalView(int articleTotalView) {
        this.articleTotalView = articleTotalView;
    }

    public int getArticleTotalShare() {
        return articleTotalShare;
    }

    public void setArticleTotalShare(int articleTotalShare) {
        this.articleTotalShare = articleTotalShare;
    }

    public int getArticleTotalLike() {
        return articleTotalLike;
    }

    public void setArticleTotalLike(int articleTotalLike) {
        this.articleTotalLike = articleTotalLike;
    }

    public String getArticleCreateUserId() {
        return articleCreateUserId;
    }

    public void setArticleCreateUserId(String articleCreateUserId) {
        this.articleCreateUserId = articleCreateUserId;
    }

    public String getArticleCreateUserName() {
        return articleCreateUserName;
    }

    public void setArticleCreateUserName(String articleCreateUserName) {
        this.articleCreateUserName = articleCreateUserName;
    }

    public int getArticleTotalComment() {
        return articleTotalComment;
    }

    public void setArticleTotalComment(int articleTotalComment) {
        this.articleTotalComment = articleTotalComment;
    }

    public String getArticleCreateUserLogo() {
        return articleCreateUserLogo;
    }

    public void setArticleCreateUserLogo(String articleCreateUserLogo) {
        this.articleCreateUserLogo = articleCreateUserLogo;
    }

    public ArticleLike() {
    }

    public ArticleLike(String id, String userId, String articleId, int likeStatus, Date createTime, String typeId, String articleTitle, String articleLogo, String articleTopicsId, String articleTopics, int articleTotalView, int articleTotalShare, int articleTotalLike, String articleCreateUserId, String articleCreateUserName, int articleTotalComment, String articleCreateUserLogo) {
        this.id = id;
        this.userId = userId;
        this.articleId = articleId;
        this.likeStatus = likeStatus;
        this.createTime = createTime;
        this.typeId = typeId;
        this.articleTitle = articleTitle;
        this.articleLogo = articleLogo;
        this.articleTopicsId = articleTopicsId;
        this.articleTopics = articleTopics;
        this.articleTotalView = articleTotalView;
        this.articleTotalShare = articleTotalShare;
        this.articleTotalLike = articleTotalLike;
        this.articleCreateUserId = articleCreateUserId;
        this.articleCreateUserName = articleCreateUserName;
        this.articleTotalComment = articleTotalComment;
        this.articleCreateUserLogo = articleCreateUserLogo;
    }
}
