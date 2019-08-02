package com.memory.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName UserFollow
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/8 19:31
 */
@Entity
@Table(name = "user_follow", schema = "xhm_db", catalog = "")
public class UserFollow {
    private String id;
    private String followUserId;
    private String attentionUserId;
    private int isFollow;
    private Date createTime;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "attention_user_id")
    public String getAttentionUserId() {
        return attentionUserId;
    }

    public void setAttentionUserId(String attentionUserId) {
        this.attentionUserId = attentionUserId;
    }

    @Basic
    @Column(name = "is_follow")
    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    @Basic
    @Column(name = "create_time")
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
        UserFollow that = (UserFollow) o;
        return isFollow == that.isFollow &&
                Objects.equals(id, that.id) &&
                Objects.equals(followUserId, that.followUserId) &&
                Objects.equals(attentionUserId, that.attentionUserId) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, followUserId, attentionUserId, isFollow, createTime);
    }
}
