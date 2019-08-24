package com.memory.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName UserGroup
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/8/3 14:04
 */
@Entity
@Table(name = "user_group", schema = "xhm_db", catalog = "")
public class UserGroup {
    private String id;
    private String userId;
    private String parentIdOne;
    private String parentIdTwo;
    private double royaltyOne;
    private double royaltyTwo;
    private Date createTime;

    public UserGroup() {
    }

    public UserGroup(String id, String userId, String parentIdOne, String parentIdTwo, double royaltyOne, double royaltyTwo, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.parentIdOne = parentIdOne;
        this.parentIdTwo = parentIdTwo;
        this.royaltyOne = royaltyOne;
        this.royaltyTwo = royaltyTwo;
        this.createTime = createTime;
    }

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "parent_id_one")
    public String getParentIdOne() {
        return parentIdOne;
    }

    public void setParentIdOne(String parentIdOne) {
        this.parentIdOne = parentIdOne;
    }

    @Basic
    @Column(name = "parent_id_two")
    public String getParentIdTwo() {
        return parentIdTwo;
    }

    public void setParentIdTwo(String parentIdTwo) {
        this.parentIdTwo = parentIdTwo;
    }

    @Basic
    @Column(name = "royalty_one")
    public double getRoyaltyOne() {
        return royaltyOne;
    }

    public void setRoyaltyOne(double royaltyOne) {
        this.royaltyOne = royaltyOne;
    }

    @Basic
    @Column(name = "royalty_two")
    public double getRoyaltyTwo() {
        return royaltyTwo;
    }

    public void setRoyaltyTwo(double royaltyTwo) {
        this.royaltyTwo = royaltyTwo;
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
        UserGroup userGroup = (UserGroup) o;
        return Double.compare(userGroup.royaltyOne, royaltyOne) == 0 &&
                Double.compare(userGroup.royaltyTwo, royaltyTwo) == 0 &&
                Objects.equals(id, userGroup.id) &&
                Objects.equals(userId, userGroup.userId) &&
                Objects.equals(parentIdOne, userGroup.parentIdOne) &&
                Objects.equals(parentIdTwo, userGroup.parentIdTwo) &&
                Objects.equals(createTime, userGroup.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, parentIdOne, parentIdTwo, royaltyOne, royaltyTwo, createTime);
    }
}
