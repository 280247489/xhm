package com.memory.app.controller;

import com.memory.app.service.GoodsService;
import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GoodsController
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/10 19:04
 */
@RestController
@RequestMapping(value = "goods")
public class GoodsController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(GoodsController.class);
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private DaoUtils daoUtils;

    @RequestMapping(value = "sel",method = RequestMethod.POST )
    public Message sel(@RequestParam(name = "name") String goodsName, @RequestParam(name="start") Integer start, @RequestParam(name="limit") Integer limit) {
        msg = Message.success();
        Map<String, Object> map = goodsService.findGoodsList(start, limit, goodsName);
        map.put("fileUrl", this.getFileUrl());
        msg.setResult(map);
        msg.setMsg("查询成功");
        logger.info("sel{ start: {} - limit: {} }", start, limit);
        return msg;
    }
    @RequestMapping(value = "byId",method = RequestMethod.POST )
    public Message byId(@RequestParam String id){
        msg = Message.success();
        Map<String,Object> returnMap = new HashMap<>();
        Goods goods = (Goods) daoUtils.getById("Goods",id);
        returnMap.put("goods",goods);
        returnMap.put("fileUrl",this.getFileUrl());
        msg.setResult(returnMap);
        msg.setMsg("查询成功");
        logger.info("byId{ id: {}  }", id);
        return msg;
    }

    @RequestMapping(value = "getGoodsList",method = RequestMethod.POST )
    public Message getGoodsList(@RequestParam(name = "goList") String goodsList) {
        msg = Message.success();
        Map<String, Object> map = goodsService.getGoodsList(goodsList);
        map.put("fileUrl", this.getFileUrl());
        msg.setResult(map);
        msg.setMsg("查询成功");
        return msg;
    }
}
