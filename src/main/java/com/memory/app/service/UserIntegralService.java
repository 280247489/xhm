package com.memory.app.service;

import java.util.Map;

/**
 * @ClassName UserIntegralService
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/8/11 18:19
 */
public interface UserIntegralService {
    Map<String,Object> userIntegralList(String userId, Integer type, Integer start, Integer limit);
}
