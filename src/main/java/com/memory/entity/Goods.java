package com.memory.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName Goods
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/6/27 21:14
 */
@Entity
public class Goods {
    private String id;
    private String goodsName;
    private String goodsDescribe;
    private String goodsImg;
    private double goodsPrice;
    private double goodsCurrentPrice;
    private String goodsVideo;
    private String goodsDetails;
    private int goodsIsShelf;
    private Date goodsCreateTime;
    private String goodsCreateId;
    private Date goodsUpdateTime;
    private String goodsUpdateId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "goods_describe")
    public String getGoodsDescribe() {
        return goodsDescribe;
    }

    public void setGoodsDescribe(String goodsDescribe) {
        this.goodsDescribe = goodsDescribe;
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
    @Column(name = "goods_current_price")
    public double getGoodsCurrentPrice() {
        return goodsCurrentPrice;
    }

    public void setGoodsCurrentPrice(double goodsCurrentPrice) {
        this.goodsCurrentPrice = goodsCurrentPrice;
    }

    @Basic
    @Column(name = "goods_video")
    public String getGoodsVideo() {
        return goodsVideo;
    }

    public void setGoodsVideo(String goodsVideo) {
        this.goodsVideo = goodsVideo;
    }

    @Basic
    @Column(name = "goods_details")
    public String getGoodsDetails() {
        return goodsDetails;
    }

    public void setGoodsDetails(String goodsDetails) {
        this.goodsDetails = goodsDetails;
    }

    @Basic
    @Column(name = "goods_is_shelf")
    public int getGoodsIsShelf() {
        return goodsIsShelf;
    }

    public void setGoodsIsShelf(int goodsIsShelf) {
        this.goodsIsShelf = goodsIsShelf;
    }

    @Basic
    @Column(name = "goods_create_time")
    public Date getGoodsCreateTime() {
        return goodsCreateTime;
    }

    public void setGoodsCreateTime(Date goodsCreateTime) {
        this.goodsCreateTime = goodsCreateTime;
    }

    @Basic
    @Column(name = "goods_create_id")
    public String getGoodsCreateId() {
        return goodsCreateId;
    }

    public void setGoodsCreateId(String goodsCreateId) {
        this.goodsCreateId = goodsCreateId;
    }

    @Basic
    @Column(name = "goods_update_time")
    public Date getGoodsUpdateTime() {
        return goodsUpdateTime;
    }

    public void setGoodsUpdateTime(Date goodsUpdateTime) {
        this.goodsUpdateTime = goodsUpdateTime;
    }

    @Basic
    @Column(name = "goods_update_id")
    public String getGoodsUpdateId() {
        return goodsUpdateId;
    }

    public void setGoodsUpdateId(String goodsUpdateId) {
        this.goodsUpdateId = goodsUpdateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Double.compare(goods.goodsPrice, goodsPrice) == 0 &&
                Double.compare(goods.goodsCurrentPrice, goodsCurrentPrice) == 0 &&
                goodsIsShelf == goods.goodsIsShelf &&
                Objects.equals(id, goods.id) &&
                Objects.equals(goodsName, goods.goodsName) &&
                Objects.equals(goodsDescribe, goods.goodsDescribe) &&
                Objects.equals(goodsImg, goods.goodsImg) &&
                Objects.equals(goodsVideo, goods.goodsVideo) &&
                Objects.equals(goodsDetails, goods.goodsDetails) &&
                Objects.equals(goodsCreateTime, goods.goodsCreateTime) &&
                Objects.equals(goodsCreateId, goods.goodsCreateId) &&
                Objects.equals(goodsUpdateTime, goods.goodsUpdateTime) &&
                Objects.equals(goodsUpdateId, goods.goodsUpdateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsName, goodsDescribe, goodsImg, goodsPrice, goodsCurrentPrice, goodsVideo, goodsDetails, goodsIsShelf, goodsCreateTime, goodsCreateId, goodsUpdateTime, goodsUpdateId);
    }
}
