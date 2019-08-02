package com.memory.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * @author INS6+
 * @date 2019/8/2 17:33
 */

@Entity
public class Topics {
    private String id;
    private String topicName;
    private String topicsImg;
    private String topicsMsg;
    private String articleTypeId;
    private int topicSum;
    private String topicCreateUserId;
    private String topicCreateUser;
    private int topicSort;
    private int topicStatus;
    private Date topicCreateTime;
    private int topicJoinSum;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "topic_name")
    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Basic
    @Column(name = "topics_img")
    public String getTopicsImg() {
        return topicsImg;
    }

    public void setTopicsImg(String topicsImg) {
        this.topicsImg = topicsImg;
    }

    @Basic
    @Column(name = "topics_msg")
    public String getTopicsMsg() {
        return topicsMsg;
    }

    public void setTopicsMsg(String topicsMsg) {
        this.topicsMsg = topicsMsg;
    }

    @Basic
    @Column(name = "article_type_id")
    public String getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(String articleTypeId) {
        this.articleTypeId = articleTypeId;
    }

    @Basic
    @Column(name = "topic_sum")
    public int getTopicSum() {
        return topicSum;
    }

    public void setTopicSum(int topicSum) {
        this.topicSum = topicSum;
    }

    @Basic
    @Column(name = "topic_create_user_id")
    public String getTopicCreateUserId() {
        return topicCreateUserId;
    }

    public void setTopicCreateUserId(String topicCreateUserId) {
        this.topicCreateUserId = topicCreateUserId;
    }

    @Basic
    @Column(name = "topic_create_user")
    public String getTopicCreateUser() {
        return topicCreateUser;
    }

    public void setTopicCreateUser(String topicCreateUser) {
        this.topicCreateUser = topicCreateUser;
    }

    @Basic
    @Column(name = "topic_sort")
    public int getTopicSort() {
        return topicSort;
    }

    public void setTopicSort(int topicSort) {
        this.topicSort = topicSort;
    }

    @Basic
    @Column(name = "topic_status")
    public int getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(int topicStatus) {
        this.topicStatus = topicStatus;
    }

    @Basic
    @Column(name = "topic_create_time")
    public Date getTopicCreateTime() {
        return topicCreateTime;
    }

    public void setTopicCreateTime(Date topicCreateTime) {
        this.topicCreateTime = topicCreateTime;
    }

    @Basic
    @Column(name = "topic_join_sum")
    public int getTopicJoinSum() {
        return topicJoinSum;
    }

    public void setTopicJoinSum(int topicJoinSum) {
        this.topicJoinSum = topicJoinSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topics topics = (Topics) o;
        return topicSum == topics.topicSum &&
                topicSort == topics.topicSort &&
                topicStatus == topics.topicStatus &&
                topicJoinSum == topics.topicJoinSum &&
                Objects.equals(id, topics.id) &&
                Objects.equals(topicName, topics.topicName) &&
                Objects.equals(topicsImg, topics.topicsImg) &&
                Objects.equals(topicsMsg, topics.topicsMsg) &&
                Objects.equals(articleTypeId, topics.articleTypeId) &&
                Objects.equals(topicCreateUserId, topics.topicCreateUserId) &&
                Objects.equals(topicCreateUser, topics.topicCreateUser) &&
                Objects.equals(topicCreateTime, topics.topicCreateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topicName, topicsImg, topicsMsg, articleTypeId, topicSum, topicCreateUserId, topicCreateUser, topicSort, topicStatus, topicCreateTime, topicJoinSum);
    }
}
