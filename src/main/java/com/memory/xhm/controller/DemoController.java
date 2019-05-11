package com.memory.xhm.controller;

import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import com.memory.redis.config.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

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
    public Message test() {
        msg = Message.success();
        try {
            logger.error("这是ERROR");
            System.out.println("=====================");
            System.out.println(ResourceUtils.getURL("classpath:").getPath());
            System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());
            System.out.println(DemoController.class.getResource("").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        msg.setData(redisUtil.get("name"));
        return msg;
    }
}