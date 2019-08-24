package com.memory.app.controller;

import com.memory.app.service.UserIntegralService;
import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName DetailsController
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/8/22 21:49
 */
@RestController
@RequestMapping(value = "details")
public class DetailsController extends BaseController {

    @Autowired
    private UserIntegralService userIntegralService;

    @RequestMapping(value = "list",method = RequestMethod.POST)
    public Message list(@RequestParam String userId, @RequestParam Integer type, @RequestParam Integer start, @RequestParam Integer limit){
        try {
            msg = Message.success();
            Map<String,Object> returnMap = userIntegralService.userIntegralList(userId, type, start, limit);
            msg.setRecode(0);
            msg.setResult(returnMap);
        }catch (Exception e){
            msg = Message.error();
            e.printStackTrace();
        }
        return msg;
    }
}
