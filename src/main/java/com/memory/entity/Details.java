package com.memory.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName Details
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/23 19:37
 */
@Entity
public class Details {
    private String id;
    private String type;
    private String typeId;
    private double sum;
    private String userId;
    private double userIntegral;
    private int isComplete;
    private Date createTime;
    private String createId;

    public Details() {
    }

    public Details(String id, String type, String typeId, double sum, String userId, double userIntegral, int isComplete, Date createTime, String createId) {
        this.id = id;
        this.type = type;
        this.typeId = typeId;
        this.sum = sum;
        this.userId = userId;
        this.userIntegral = userIntegral;
        this.isComplete = isComplete;
        this.createTime = createTime;
        this.createId = createId;
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "type_id")
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "sum")
    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
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
    @Column(name = "user_integral")
    public double getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(double userIntegral) {
        this.userIntegral = userIntegral;
    }

    @Basic
    @Column(name = "is_complete")
    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
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
    @Column(name = "create_id")
    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Details details = (Details) o;
        return Double.compare(details.sum, sum) == 0 &&
                Double.compare(details.userIntegral, userIntegral) == 0 &&
                isComplete == details.isComplete &&
                Objects.equals(id, details.id) &&
                Objects.equals(type, details.type) &&
                Objects.equals(typeId, details.typeId) &&
                Objects.equals(userId, details.userId) &&
                Objects.equals(createTime, details.createTime) &&
                Objects.equals(createId, details.createId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, typeId, sum, userId, userIntegral, isComplete, createTime, createId);
    }
}
