package com.memory.app.service;

import java.util.Map;

/**
 * @ClassName GoodsService
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/10 18:00
 */
public interface GoodsService {
    Map<String,Object> findGoodsList(Integer start, Integer limit, String goodsName);
}
