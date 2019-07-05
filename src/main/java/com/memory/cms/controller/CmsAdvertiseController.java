package com.memory.cms.controller;

import com.memory.cms.service.CmsAdvertiseService;
import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import com.memory.common.utils.Utils;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.Advertise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @ClassName CmsAdvertiseController
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/6/29 18:31
 */
@RestController
@RequestMapping(value = "cmsAdvertise")
public class CmsAdvertiseController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(CmsAdvertiseController.class);
    @Autowired
    private CmsAdvertiseService advertiseService;

    @Autowired
    private DaoUtils daoUtils;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Message add(@RequestParam String name,@RequestParam MultipartFile logo,@RequestParam String content, @RequestParam String cid){
        try {
            msg = Message.success();
            Advertise advertise = new Advertise();
            Date date = new Date();
            String id =Utils.getShortUUID();
            advertise.setId(id);
            advertise.setAdvertiseName(name);
            if (logo!=null){
                advertise.setAdvertiseLogo(this.upload2PNG(Utils.idTimer.format(date)+"","xhm_file/article/"+id,logo));
            }else {
                advertise.setAdvertiseLogo("");
            }
            advertise.setAdvertiseContent(content);
            advertise.setAdvertiseIsOnline(0);
            advertise.setAdvertiseCreateTime(date);
            advertise.setAdvertiseCreateId(cid);
            advertise.setAdvertiseUpdateTime(date);
            advertise.setAdvertiseUpdateId(cid);

            advertiseService.add(advertise);
            msg.setRecode(0);
        }catch (Exception e){
            msg = Message.error();
            e.printStackTrace();
            logger.error("errot" + e.toString());
        }
        return msg;
    }
    @RequestMapping(value = "upd",method = RequestMethod.POST)
    public Message upd(@RequestParam String id,@RequestParam String name, @RequestParam MultipartFile logo,@RequestParam String content,@RequestParam String cid){
        try {
            msg = Message.success();
            Advertise advertise = (Advertise) daoUtils.getById("Advertise",id);
            Date date = new Date();
            advertise.setAdvertiseName(name);
            if (logo!=null){
                advertise.setAdvertiseLogo(this.upload2PNG(Utils.idTimer.format(date)+"","xhm_file/article/"+id,logo));
            }else {
                advertise.setAdvertiseLogo("");
            }
            advertise.setAdvertiseContent(content);
            advertise.setAdvertiseUpdateTime(date);
            advertise.setAdvertiseUpdateId(cid);

            advertiseService.add(advertise);
            msg.setRecode(0);
        }catch (Exception e){
            msg = Message.error();
            e.printStackTrace();
            logger.error("errot" + e.toString());
        }
        return msg;
    }
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public Message list(@RequestParam String name, @RequestParam Integer start,@RequestParam Integer limit){
        try {
            msg = Message.success();
            msg.setResult(advertiseService.list(start, limit, name));
            msg.setRecode(0);

        }catch (Exception e){
            msg = Message.error();
            e.printStackTrace();
            logger.error("errot" + e.toString());
        }
        return msg;
    }

    @RequestMapping(value = "online",method = RequestMethod.POST)
    public Message online(@RequestParam String id){
        try {
            msg = Message.success();
            msg.setResult(advertiseService.online(id));
            msg.setRecode(0);
        }catch (Exception e){
            msg = Message.error();
            e.printStackTrace();
            logger.error("errot" + e.toString());
        }
        return msg;
    }
}
