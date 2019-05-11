package com.memory.xhm.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 10:20
 * @Description:
 */
@Entity
@Table(name = "article_comment", schema = "xhm_db", catalog = "")
public class ArticleComment {
    private String id;
    private String userId;
    private String userLogo;
    private String userName;
    private String articleId;
    private int commentType;
    private String commentParentId;
    private String commentContent;
    private Timestamp commentCreateTime;
    private int commentTotalLike;

    @Id
    @Column(name = "id", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false, length = 255)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_logo", nullable = false, length = 255)
    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "article_id", nullable = false, length = 255)
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Basic
    @Column(name = "comment_type", nullable = false)
    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

    @Basic
    @Column(name = "comment_parent_id", nullable = false, length = 255)
    public String getCommentParentId() {
        return commentParentId;
    }

    public void setCommentParentId(String commentParentId) {
        this.commentParentId = commentParentId;
    }

    @Basic
    @Column(name = "comment_content", nullable = false, length = 255)
    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @Basic
    @Column(name = "comment_create_time", nullable = false)
    public Timestamp getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(Timestamp commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }

    @Basic
    @Column(name = "comment_total_like", nullable = false)
    public int getCommentTotalLike() {
        return commentTotalLike;
    }

    public void setCommentTotalLike(int commentTotalLike) {
        this.commentTotalLike = commentTotalLike;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleComment that = (ArticleComment) o;
        return commentType == that.commentType &&
                commentTotalLike == that.commentTotalLike &&
                Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userLogo, that.userLogo) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(articleId, that.articleId) &&
                Objects.equals(commentParentId, that.commentParentId) &&
                Objects.equals(commentContent, that.commentContent) &&
                Objects.equals(commentCreateTime, that.commentCreateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, userLogo, userName, articleId, commentType, commentParentId, commentContent, commentCreateTime, commentTotalLike);
    }
}
