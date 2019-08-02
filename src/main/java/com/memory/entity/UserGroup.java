package com.memory.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName UserGroup
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/27 16:28
 */
@Entity
@Table(name = "user_group", schema = "xhm_db", catalog = "")
public class UserGroup {
    private String id;
    private String userId;
    private String parentIdOne;
    private String parentIdTwo;
    private int royalty;
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
    @Column(name = "royalty")
    public int getRoyalty() {
        return royalty;
    }

    public void setRoyalty(int royalty) {
        this.royalty = royalty;
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
        return royalty == userGroup.royalty &&
                Objects.equals(id, userGroup.id) &&
                Objects.equals(userId, userGroup.userId) &&
                Objects.equals(parentIdOne, userGroup.parentIdOne) &&
                Objects.equals(parentIdTwo, userGroup.parentIdTwo) &&
                Objects.equals(createTime, userGroup.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, parentIdOne, parentIdTwo, royalty, createTime);
    }
}
