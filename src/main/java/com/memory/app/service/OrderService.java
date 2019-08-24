package com.memory.app.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @ClassName OrderService
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/27 16:05
 */
public interface OrderService {
    @Transactional
    Map<String,Object> addOrder(String userId, String openId, String shrName, String shrPhone, String shrAddr, String goodsList, String remark);

    //修改订单状态 生成积分记录


    //修改订单状态 生成积分记录
    Map<String,Object> updOrder(String orderNo, String transactionId);

    Map<String,Object> getOrderById(String orderMasterId);

    Map<String,Object> orderListByUserId(String userId, Integer orderStatus, Integer start, Integer limit);

    int updOrderStatus(String orderId);

    int delOrder(String orderId);
}
