package com.memory.app.controller;

import com.memory.app.repository.AdvertiseRepository;
import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AdvertiseController
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/6/29 19:11
 */
@RestController
@RequestMapping(value = "advertiseController")
public class AdvertiseController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(AdvertiseController.class);
    @Autowired
    private AdvertiseRepository advertiseRepository;

    @RequestMapping(value = "getById",method = RequestMethod.POST)
    public Message getById(){
        try {
            msg = Message.success();
            msg.setRecode(0);
            msg.setResult(advertiseRepository.findByAdvertiseIsOnline(1 ));

        }catch (Exception e){
            msg = Message.error();
            e.printStackTrace();
            logger.error("error"+e.toString());
        }
        return msg;
    }
}
