package com.memory.app.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @ClassName UserCashOutService
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/8/22 23:31
 */
public interface UserCashOutService {
    @Transactional
    Integer addCashOut(String userId, double cashOutScore);

    Map<String,Object> cashOutList(String userId, Integer start, Integer limit);
}
