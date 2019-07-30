package com.memory.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName OrderSlave
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/27 16:01
 */
@Entity
@Table(name = "order_slave", schema = "xhm_db", catalog = "")
public class OrderSlave {
    private String id;
    private String orderMasterId;
    private int count;
    private double sum;
    private String goodsId;
    private String goodsName;
    private String goodsImg;
    private double goodsPrice;
    private Date createTime;
    private int delYn;

    public OrderSlave() {
    }

    public OrderSlave(String id, String orderMasterId, int count, double sum, String goodsId, String goodsName, String goodsImg, double goodsPrice, Date createTime, int delYn) {
        this.id = id;
        this.orderMasterId = orderMasterId;
        this.count = count;
        this.sum = sum;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsImg = goodsImg;
        this.goodsPrice = goodsPrice;
        this.createTime = createTime;
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
    @Column(name = "order_master_id")
    public String getOrderMasterId() {
        return orderMasterId;
    }

    public void setOrderMasterId(String orderMasterId) {
        this.orderMasterId = orderMasterId;
    }

    @Basic
    @Column(name = "count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
    @Column(name = "goods_id")
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "goods_name")
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Basic
    @Column(name = "goods_img")
    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    @Basic
    @Column(name = "goods_price")
    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
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
        OrderSlave that = (OrderSlave) o;
        return count == that.count &&
                Double.compare(that.sum, sum) == 0 &&
                Double.compare(that.goodsPrice, goodsPrice) == 0 &&
                delYn == that.delYn &&
                Objects.equals(id, that.id) &&
                Objects.equals(orderMasterId, that.orderMasterId) &&
                Objects.equals(goodsId, that.goodsId) &&
                Objects.equals(goodsName, that.goodsName) &&
                Objects.equals(goodsImg, that.goodsImg) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderMasterId, count, sum, goodsId, goodsName, goodsImg, goodsPrice, createTime, delYn);
    }
}
