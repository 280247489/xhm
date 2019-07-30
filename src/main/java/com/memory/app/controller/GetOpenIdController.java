package com.memory.app.controller;

import com.memory.app.service.GetOpenIdService;
import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName GetOpenIdController
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/30 14:15
 */
@RestController
@RequestMapping(value = "getOpenId")
public class GetOpenIdController extends BaseController {

    @Autowired
    private GetOpenIdService getOpenIdService;

    @RequestMapping(value = "getOpenId")
    public Message getOpenId (@RequestParam String code){
        try {
            msg = Message.success();
            Map<String,Object> returnMap = getOpenIdService.getOpenId(code);
            msg.setRecode(0);
            msg.setResult(returnMap);
        }catch (Exception e){
            msg = Message.error();
            e.printStackTrace();
        }
        return msg;
    }
}
