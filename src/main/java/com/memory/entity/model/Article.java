package com.memory.entity.model;

import java.util.Date;

/**
 * @author INS6+
 * @date 2019/7/25 14:33
 */

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
    private String articleCreateUserLogo;
    private int articleTotaolDz;

    public Article() {
    }

    public Article(String id, String articleTitle, String articleTopics, Date articleCreateTime) {
        this.id = id;
        this.articleTitle = articleTitle;
        this.articleTopics = articleTopics;
        this.articleCreateTime = articleCreateTime;
    }

    public Article(String id, String typeId, String articleTitle, String articleLogo, String articlePicture, String articleContent, String articleTopicsId, String articleTopics, String articleLabel, String articleKeyWords, int articleOnline, int articleTotalView, int articleTotalShare, int articleTotalLike, Date articleCreateTime, String articleCreateUserId, int articleCheckYn, Date articleCheckTime, String articleCheckAdminId, int articleDelYn, int articleTopYn, String articleCreateUserName, int articleTotalComment, String articleCreateUserLogo) {
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
        this.articleCreateUserName = articleCreateUserName;
        this.articleTotalComment = articleTotalComment;
        this.articleCreateUserLogo = articleCreateUserLogo;
    }

    public Article(String id, String typeId, String articleTitle, String articleLogo, String articlePicture, String articleContent, String articleTopicsId, String articleTopics, String articleLabel, String articleKeyWords, int articleOnline, int articleTotalView, int articleTotalShare, int articleTotalLike, Date articleCreateTime, String articleCreateUserId, int articleCheckYn, Date articleCheckTime, String articleCheckAdminId, int articleDelYn, int articleTopYn, String articleCreateUserName, int articleTotalComment, String articleCreateUserLogo, int articleTotaolDz) {
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
        this.articleCreateUserName = articleCreateUserName;
        this.articleTotalComment = articleTotalComment;
        this.articleCreateUserLogo = articleCreateUserLogo;
        this.articleTotaolDz = articleTotaolDz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getArticlePicture() {
        return articlePicture;
    }

    public void setArticlePicture(String articlePicture) {
        this.articlePicture = articlePicture;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
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

    public String getArticleLabel() {
        return articleLabel;
    }

    public void setArticleLabel(String articleLabel) {
        this.articleLabel = articleLabel;
    }

    public String getArticleKeyWords() {
        return articleKeyWords;
    }

    public void setArticleKeyWords(String articleKeyWords) {
        this.articleKeyWords = articleKeyWords;
    }

    public int getArticleOnline() {
        return articleOnline;
    }

    public void setArticleOnline(int articleOnline) {
        this.articleOnline = articleOnline;
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

    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public String getArticleCreateUserId() {
        return articleCreateUserId;
    }

    public void setArticleCreateUserId(String articleCreateUserId) {
        this.articleCreateUserId = articleCreateUserId;
    }

    public int getArticleCheckYn() {
        return articleCheckYn;
    }

    public void setArticleCheckYn(int articleCheckYn) {
        this.articleCheckYn = articleCheckYn;
    }

    public Date getArticleCheckTime() {
        return articleCheckTime;
    }

    public void setArticleCheckTime(Date articleCheckTime) {
        this.articleCheckTime = articleCheckTime;
    }

    public String getArticleCheckAdminId() {
        return articleCheckAdminId;
    }

    public void setArticleCheckAdminId(String articleCheckAdminId) {
        this.articleCheckAdminId = articleCheckAdminId;
    }

    public int getArticleDelYn() {
        return articleDelYn;
    }

    public void setArticleDelYn(int articleDelYn) {
        this.articleDelYn = articleDelYn;
    }

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
}
