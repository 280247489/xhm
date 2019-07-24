package com.memory.entity.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author INS6+
 * @date 2019/7/23 19:44
 */

public class UserCollection {
    private String id;
    private String collectionUserId;
    private String attentionUserId;
    private int isFollow;
    private Date createTime;

    private String userName;
    private String userLogo;
    private String userId;

    public UserCollection() {
    }

    public UserCollection(String id, String collectionUserId, String attentionUserId, int isFollow, Date createTime, String userName, String userLogo, String userId) {
        this.id = id;
        this.collectionUserId = collectionUserId;
        this.attentionUserId = attentionUserId;
        this.isFollow = isFollow;
        this.createTime = createTime;
        this.userName = userName;
        this.userLogo = userLogo;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollectionUserId() {
        return collectionUserId;
    }

    public void setCollectionUserId(String collectionUserId) {
        this.collectionUserId = collectionUserId;
    }

    public String getAttentionUserId() {
        return attentionUserId;
    }

    public void setAttentionUserId(String attentionUserId) {
        this.attentionUserId = attentionUserId;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
