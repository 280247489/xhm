package com.memory.xhm.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import com.memory.common.utils.Utils;
import com.memory.redis.config.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: cui.Memory
 * @Date: 2018/11/1
 * @Description:
 */
@RestController
public class DemoController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(DemoController.class);
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("test")
    public Message test(HttpServletRequest req) {
        msg = Message.success();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            /*map.put("req.getContextPath()", req.getContextPath());
            map.put("req.getServletPath()", req.getServletPath());
            map.put("req.getServletContext().getRealPath('')", req.getServletContext().getRealPath(""));
            map.put("ResourceUtils.getURL('classpath:').getPath()", ResourceUtils.getURL("classpath:").getPath());
            map.put("ClassUtils.getDefaultClassLoader().getResource('').getPath()", ClassUtils.getDefaultClassLoader().getResource("").getPath());
            map.put("DemoController.class.getResource('').getPath()", DemoController.class.getResource("").getPath());
            logger.error("这是ERROR");*/

            /*
            //添加测试信息
            Map<String, Object> article_map = new HashMap<String, Object>();
            for (int i=0; i<50; i++){
                article_map.put(i+"_key", i+"_"+ Utils.getShortUUID());
            }

            redisUtil.set("article:content:id-1", JSON.toJSONString(article_map));
            redisUtil.set("article:content:id-2", JSON.toJSONString(article_map));
            redisUtil.set("article:content:id-3", JSON.toJSONString(article_map));*/

            map.put("content", JSON.parse(redisUtil.get("article:content:id-1").toString()));
            map.put("view", redisUtil.incr("article:view:id-1", 1));

        } catch (Exception e) {
            e.printStackTrace();
        }
        msg.setData(map);
        return msg;
    }
}