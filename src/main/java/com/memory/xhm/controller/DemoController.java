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

    @RequestMapping("test")
    public Message test(HttpServletRequest req) {
        msg = Message.success();
        Map<String, String> map = new HashMap<String, String>();
        try {
            map.put("req.getContextPath()", req.getContextPath());
            map.put("req.getServletPath()", req.getServletPath());
            map.put("req.getServletContext().getRealPath('')", req.getServletContext().getRealPath(""));
            map.put("ResourceUtils.getURL('classpath:').getPath()", ResourceUtils.getURL("classpath:").getPath());
            map.put("ClassUtils.getDefaultClassLoader().getResource('').getPath()", ClassUtils.getDefaultClassLoader().getResource("").getPath());
            map.put("DemoController.class.getResource('').getPath()", DemoController.class.getResource("").getPath());
            logger.error("这是ERROR");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        msg.setData(map);
        return msg;
    }
}