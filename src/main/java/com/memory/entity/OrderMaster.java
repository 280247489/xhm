package com.memory.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName OrderMaster
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/29 18:49
 */
@Entity
@Table(name = "order_master", schema = "xhm_db", catalog = "")
public class OrderMaster {
    private String id;
    private String userId;
    private String orderNo;
    private String shrName;
    private String shrPhone;
    private String shrAddr;
    private double orderMoney;
    private int orderStatus;
    private String remark;
    private String wxNonceStr;
    private String wxSign;
    private String wxPrepayId;
    private String wxTransactionId;
    private Date createTime;
    private Date payTime;
    private int delYn;

    public OrderMaster() {
    }

    public OrderMaster(String id, String userId, String orderNo, String shrName, String shrPhone, String shrAddr, double orderMoney, int orderStatus, String remark, String wxNonceStr, String wxSign, String wxPrepayId, String wxTransactionId, Date createTime, Date payTime, int delYn) {
        this.id = id;
        this.userId = userId;
        this.orderNo = orderNo;
        this.shrName = shrName;
        this.shrPhone = shrPhone;
        this.shrAddr = shrAddr;
        this.orderMoney = orderMoney;
        this.orderStatus = orderStatus;
        this.remark = remark;
        this.wxNonceStr = wxNonceStr;
        this.wxSign = wxSign;
        this.wxPrepayId = wxPrepayId;
        this.wxTransactionId = wxTransactionId;
        this.createTime = createTime;
        this.payTime = payTime;
        this.delYn = delYn;
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
    @Column(name = "order_no")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "shr_name")
    public String getShrName() {
        return shrName;
    }

    public void setShrName(String shrName) {
        this.shrName = shrName;
    }

    @Basic
    @Column(name = "shr_phone")
    public String getShrPhone() {
        return shrPhone;
    }

    public void setShrPhone(String shrPhone) {
        this.shrPhone = shrPhone;
    }

    @Basic
    @Column(name = "shr_addr")
    public String getShrAddr() {
        return shrAddr;
    }

    public void setShrAddr(String shrAddr) {
        this.shrAddr = shrAddr;
    }

    @Basic
    @Column(name = "order_money")
    public double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(double orderMoney) {
        this.orderMoney = orderMoney;
    }

    @Basic
    @Column(name = "order_status")
    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "wx_nonce_str")
    public String getWxNonceStr() {
        return wxNonceStr;
    }

    public void setWxNonceStr(String wxNonceStr) {
        this.wxNonceStr = wxNonceStr;
    }

    @Basic
    @Column(name = "wx_sign")
    public String getWxSign() {
        return wxSign;
    }

    public void setWxSign(String wxSign) {
        this.wxSign = wxSign;
    }

    @Basic
    @Column(name = "wx_prepay_id")
    public String getWxPrepayId() {
        return wxPrepayId;
    }

    public void setWxPrepayId(String wxPrepayId) {
        this.wxPrepayId = wxPrepayId;
    }

    @Basic
    @Column(name = "wx_transaction_id")
    public String getWxTransactionId() {
        return wxTransactionId;
    }

    public void setWxTransactionId(String wxTransactionId) {
        this.wxTransactionId = wxTransactionId;
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
    @Column(name = "pay_time")
    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Basic
    @Column(name = "del_yn")
    public int getDelYn() {
        return delYn;
    }

    public void setDelYn(int delYn) {
        this.delYn = delYn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderMaster that = (OrderMaster) o;
        return Double.compare(that.orderMoney, orderMoney) == 0 &&
                orderStatus == that.orderStatus &&
                delYn == that.delYn &&
                Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(orderNo, that.orderNo) &&
                Objects.equals(shrName, that.shrName) &&
                Objects.equals(shrPhone, that.shrPhone) &&
                Objects.equals(shrAddr, that.shrAddr) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(wxNonceStr, that.wxNonceStr) &&
                Objects.equals(wxSign, that.wxSign) &&
                Objects.equals(wxPrepayId, that.wxPrepayId) &&
                Objects.equals(wxTransactionId, that.wxTransactionId) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(payTime, that.payTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, orderNo, shrName, shrPhone, shrAddr, orderMoney, orderStatus, remark, wxNonceStr, wxSign, wxPrepayId, wxTransactionId, createTime, payTime, delYn);
    }
}
