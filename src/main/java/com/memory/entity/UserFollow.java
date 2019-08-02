package com.memory.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author INS6+
 * @date 2019/8/2 15:39
 */

@Entity
@Table(name = "user_follow", schema = "xhm_db", catalog = "")
public class UserFollow {
    private String id;
    private String articleId;
    private Date createTime;
    private String followUserId;
    private Integer isFollow;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "article_id")
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "follow_user_id")
    public String getFollowUserId() {
        return followUserId;
    }

    public void setFollowUserId(String followUserId) {
        this.followUserId = followUserId;
    }

    @Basic
    @Column(name = "is_follow")
    public Integer getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Integer isFollow) {
        this.isFollow = isFollow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFollow that = (UserFollow) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(articleId, that.articleId) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(followUserId, that.followUserId) &&
                Objects.equals(isFollow, that.isFollow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, articleId, createTime, followUserId, isFollow);
    }
}
