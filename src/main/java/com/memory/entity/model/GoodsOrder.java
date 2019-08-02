package com.memory.entity.model;

/**
 * @ClassName GoodsOrder
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/26 22:04
 */
public class GoodsOrder {
    private String goodsId;
    private String goodsImg;
    private String goodsName;
    private Integer count;
    private Double goodsPrice;
    private Double sum;

    public GoodsOrder() {
    }

    public GoodsOrder(String goodsId, String goodsImg, String goodsName, Integer count, Double goodsPrice, Double sum) {
        this.goodsId = goodsId;
        this.goodsImg = goodsImg;
        this.goodsName = goodsName;
        this.count = count;
        this.goodsPrice = goodsPrice;
        this.sum = sum;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
