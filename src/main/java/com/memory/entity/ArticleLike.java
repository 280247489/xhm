package com.memory.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/16 0016 15:28
 * @Description:
 */
@Entity
@Table(name = "article_like", schema = "xhm_db", catalog = "")
public class ArticleLike {
    private String id;
    private String userId;
    private String articleId;
    private int likeStatus;
    private Date createTime;

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
    @Column(name = "article_id", nullable = false, length = 255)
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Basic
    @Column(name = "like_status", nullable = false)
    public int getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(int likeStatus) {
        this.likeStatus = likeStatus;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleLike that = (ArticleLike) o;
        return likeStatus == that.likeStatus &&
                Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(articleId, that.articleId) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, articleId, likeStatus, createTime);
    }
}
