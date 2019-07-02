package com.memory.entity.model;

import java.util.Date;

/**
 * @author INS6+
 * @date 2019/7/2 17:28
 */

public class Topics {

    private String id;
    private String topicName;
    private String articleTypeId;
    private int topicSum;
    private String topicCreateUserId;
    private String topicCreateUser;
    private int topicSort;
    private int topicStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(String articleTypeId) {
        this.articleTypeId = articleTypeId;
    }

    public int getTopicSum() {
        return topicSum;
    }

    public void setTopicSum(int topicSum) {
        this.topicSum = topicSum;
    }

    public String getTopicCreateUserId() {
        return topicCreateUserId;
    }

    public void setTopicCreateUserId(String topicCreateUserId) {
        this.topicCreateUserId = topicCreateUserId;
    }

    public String getTopicCreateUser() {
        return topicCreateUser;
    }

    public void setTopicCreateUser(String topicCreateUser) {
        this.topicCreateUser = topicCreateUser;
    }

    public int getTopicSort() {
        return topicSort;
    }

    public void setTopicSort(int topicSort) {
        this.topicSort = topicSort;
    }

    public int getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(int topicStatus) {
        this.topicStatus = topicStatus;
    }

    public Topics() {
    }

    public Topics(String id, String topicName, String articleTypeId, int topicSum, String topicCreateUserId, String topicCreateUser, int topicSort, int topicStatus) {
        this.id = id;
        this.topicName = topicName;
        this.articleTypeId = articleTypeId;
        this.topicSum = topicSum;
        this.topicCreateUserId = topicCreateUserId;
        this.topicCreateUser = topicCreateUser;
        this.topicSort = topicSort;
        this.topicStatus = topicStatus;
    }
}
