package com.memory.cms.service;

import com.memory.entity.Goods;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @ClassName CmsGoodsService
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/6/25 18:30
 */
public interface CmsGoodsService {
    Goods addOrUpdate(Goods goods);

    @Transactional
    Goods update(Goods goods);

    Goods checkGoodsName(String goodsName, String id);

    Goods updShelf(Goods goods);

    Map<String,Object> findGoodsList(Integer start, Integer limit, String goodsName, Integer goodsIsShelf, String startTime, String endTime);
}
