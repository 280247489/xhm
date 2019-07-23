package com.memory.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author INS6+
 * @date 2019/7/23 19:44
 */

@Entity
@Table(name = "user_collection", schema = "xhm_db", catalog = "")
public class UserCollection {
    private String id;
    private String collectionUserId;
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
    @Column(name = "collection_user_id")
    public String getCollectionUserId() {
        return collectionUserId;
    }

    public void setCollectionUserId(String collectionUserId) {
        this.collectionUserId = collectionUserId;
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
        UserCollection that = (UserCollection) o;
        return isFollow == that.isFollow &&
                Objects.equals(id, that.id) &&
                Objects.equals(collectionUserId, that.collectionUserId) &&
                Objects.equals(attentionUserId, that.attentionUserId) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, collectionUserId, attentionUserId, isFollow, createTime);
    }
}
