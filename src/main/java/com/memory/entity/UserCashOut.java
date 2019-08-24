package com.memory.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName UserCashOut
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/8/22 23:30
 */
@Entity
@Table(name = "user_cash_out", schema = "xhm_db", catalog = "")
public class UserCashOut {
    private String id;
    private String userId;
    private double cashOutScore;
    private double cashOutMoney;
    private double cashOutSurplusScore;
    private int cashOutState;
    private Date createTime;
    private Date drawTime;
    private String operatorId;

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
    @Column(name = "cash_out_score")
    public double getCashOutScore() {
        return cashOutScore;
    }

    public void setCashOutScore(double cashOutScore) {
        this.cashOutScore = cashOutScore;
    }

    @Basic
    @Column(name = "cash_out_money")
    public double getCashOutMoney() {
        return cashOutMoney;
    }

    public void setCashOutMoney(double cashOutMoney) {
        this.cashOutMoney = cashOutMoney;
    }

    @Basic
    @Column(name = "cash_out_surplus_score")
    public double getCashOutSurplusScore() {
        return cashOutSurplusScore;
    }

    public void setCashOutSurplusScore(double cashOutSurplusScore) {
        this.cashOutSurplusScore = cashOutSurplusScore;
    }

    @Basic
    @Column(name = "cash_out_state")
    public int getCashOutState() {
        return cashOutState;
    }

    public void setCashOutState(int cashOutState) {
        this.cashOutState = cashOutState;
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
    @Column(name = "draw_time")
    public Date getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(Date drawTime) {
        this.drawTime = drawTime;
    }

    @Basic
    @Column(name = "operator_id")
    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCashOut that = (UserCashOut) o;
        return Double.compare(that.cashOutScore, cashOutScore) == 0 &&
                Double.compare(that.cashOutMoney, cashOutMoney) == 0 &&
                Double.compare(that.cashOutSurplusScore, cashOutSurplusScore) == 0 &&
                cashOutState == that.cashOutState &&
                Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(drawTime, that.drawTime) &&
                Objects.equals(operatorId, that.operatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, cashOutScore, cashOutMoney, cashOutSurplusScore, cashOutState, createTime, drawTime, operatorId);
    }
}
