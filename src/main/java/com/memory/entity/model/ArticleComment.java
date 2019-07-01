package com.memory.entity.model;


import java.util.Date;

/**
 * @author INS6+
 * @date 2019/5/25 9:27
 */

public class ArticleComment {
    private String id;
    private String userName;
    private String tel;
    private String articleTitle;
    private String commentContent;
    private int like;
    private long commentSum;
    private Date createTime;
    private String commentRootId;
    private int commentType;
    private String commentParentId;
    private String commentParentUserName;
    private String commentContentReplace;
    private String articleId;
    private String commentParentContent;


    public ArticleComment() {
    }

    public ArticleComment(String id, String userName, String tel, String articleTitle, String commentContent, String commentRootId, int like , long commentSum, Date createTime, int commentType, String commentParentId, String commentParentUserName, String commentContentReplace, String articleId, String commentParentContent) {
        this.id = id;
        this.userName = userName;
        this.tel = tel;
        this.articleTitle = articleTitle;
        this.commentContent = commentContent;
        this.commentRootId = commentRootId;
        this.like = like;
        this.commentSum = commentSum;
        this.createTime = createTime;
        this.commentType = commentType;
        this.commentParentId =commentParentId;
        this.commentParentUserName=commentParentUserName;
        this.commentContentReplace=commentContentReplace;
        this.articleId =articleId;
        this.commentParentContent =commentParentContent;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public long getCommentSum() {
        return commentSum;
    }

    public void setCommentSum(long commentSum) {
        this.commentSum = commentSum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCommentRootId() {
        return commentRootId;
    }

    public void setCommentRootId(String commentRootId) {
        this.commentRootId = commentRootId;
    }

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
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

    public String getCommentContentReplace() {
        return commentContentReplace;
    }

    public void setCommentContentReplace(String commentContentReplace) {
        this.commentContentReplace = commentContentReplace;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }


    public String getCommentParentContent() {
        return commentParentContent;
    }

    public void setCommentParentContent(String commentParentContent) {
        this.commentParentContent = commentParentContent;
    }

    @Override
    public String toString() {
        return "ArticleComment{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", tel='" + tel + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", like=" + like +
                ", commentSum=" + commentSum +
                ", createTime=" + createTime +
                ", commentRootId='" + commentRootId + '\'' +
                ", commentType=" + commentType +
                ", commentParentId='" + commentParentId + '\'' +
                ", commentParentUserName='" + commentParentUserName + '\'' +
                '}';
    }
}
