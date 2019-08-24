package com.memory.app.controller;

import com.memory.app.service.UserCashOutService;
import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName UserCashOutController
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/8/22 23:47
 */
@RestController
@RequestMapping("cashOut")
public class UserCashOutController extends BaseController {
    @Autowired
    private UserCashOutService userCashOutService;

    @RequestMapping(value = "addCashOut",method = RequestMethod.POST)
    public Message addCashOut(@RequestParam String userId, @RequestParam double cashOutScore){
        try {
            msg = Message.success();
            Integer addYn = userCashOutService.addCashOut(userId, cashOutScore);
            msg.setRecode(addYn);
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping(value = "cashOutList",method = RequestMethod.POST)
    public Message cashOutList(@RequestParam String userId, @RequestParam Integer start, @RequestParam Integer limit){
        try {
            msg = Message.success();
            Map<String,Object> map = userCashOutService.cashOutList(userId, start, limit);
            msg.setRecode(0);
            msg.setResult(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }
}
