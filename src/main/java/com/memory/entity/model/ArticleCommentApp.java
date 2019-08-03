package com.memory.entity.model;


import java.util.Date;

/**
 * @author INS6+
 * @date 2019/5/25 9:27
 */

public class ArticleCommentApp {
    private String id;
    private String articleId;
    private String userId;
    private String userLogo;
    private String userName;
    private int commentType;
    private String commentRootId;
    private String commentParentId;
    private String commentParentUserName;
    private String commentContent;
    private String commentParentContent;
    private String commentContentReplace;
    private Date commentCreateTime;
    private int commentTotalLike;
    private long commentSum;
    private boolean liked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

    public String getCommentRootId() {
        return commentRootId;
    }

    public void setCommentRootId(String commentRootId) {
        this.commentRootId = commentRootId;
    }

    public String getCommentParentId() {
        return commentParentId;
    }

    public void setCommentParentId(String commentParentId) {
        this.commentParentId = commentParentId;
    }

    public String getCommentParentUserName() {
        return commentParentUserName;
    }

    public void setCommentParentUserName(String commentParentUserName) {
        this.commentParentUserName = commentParentUserName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentParentContent() {
        return commentParentContent;
    }

    public void setCommentParentContent(String commentParentContent) {
        this.commentParentContent = commentParentContent;
    }

    public String getCommentContentReplace() {
        return commentContentReplace;
    }

    public void setCommentContentReplace(String commentContentReplace) {
        this.commentContentReplace = commentContentReplace;
    }

    public Date getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(Date commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }

    public int getCommentTotalLike() {
        return commentTotalLike;
    }

    public void setCommentTotalLike(int commentTotalLike) {
        this.commentTotalLike = commentTotalLike;
    }

    public long getCommentSum() {
        return commentSum;
    }

    public void setCommentSum(long commentSum) {
        this.commentSum = commentSum;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public ArticleCommentApp(String id, String articleId, String userId, String userLogo, String userName, int commentType, String commentRootId, String commentParentId, String commentParentUserName, String commentContent, String commentParentContent, String commentContentReplace, Date commentCreateTime, int commentTotalLike, long commentSum) {
        this.id = id;
        this.articleId = articleId;
        this.userId = userId;
        this.userLogo = userLogo;
        this.userName = userName;
        this.commentType = commentType;
        this.commentRootId = commentRootId;
        this.commentParentId = commentParentId;
        this.commentParentUserName = commentParentUserName;
        this.commentContent = commentContent;
        this.commentParentContent = commentParentContent;
        this.commentContentReplace = commentContentReplace;
        this.commentCreateTime = commentCreateTime;
        this.commentTotalLike = commentTotalLike;
        this.commentSum = commentSum;
    }

    public ArticleCommentApp() {
    }
}
