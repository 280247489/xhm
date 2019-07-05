package com.memory.cms.controller;

import com.memory.cms.service.CmsGoodsService;
import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import com.memory.common.utils.Utils;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CmsGoodsController
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/6/25 18:54
 */
@RestController
@RequestMapping(value = "cmsGoods")
public class CmsGoodsController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(CmsGoodsController.class);

    @Autowired
    private CmsGoodsService cmsGoodsService;

    @Autowired
    private DaoUtils daoUtils;

    @RequestMapping(value = "add", method = RequestMethod.POST )
    public Message add(@RequestParam String goodsName, @RequestParam String goodsDescribe, @RequestParam List<MultipartFile> files,@RequestParam MultipartFile video,
                       @RequestParam double goodsPrice, @RequestParam double goodsCurrentPrice, @RequestParam String goodsDetails, @RequestParam String id){
        try {
            msg = Message.success();
            Goods goods = cmsGoodsService.checkGoodsName(goodsName,"");
            if (goods==null){
                Date date = new Date();
                goods = new Goods();
                goods.setId(Utils.getShortUUID());
                goods.setGoodsName(goodsName);
                goods.setGoodsDescribe(goodsDescribe);
                goods.setGoodsPrice(goodsPrice);
                goods.setGoodsCurrentPrice(goodsCurrentPrice);
                goods.setGoodsDetails(goodsDetails);
                goods.setGoodsIsShelf(0);
                goods.setGoodsCreateTime(date);
                goods.setGoodsCreateId(id);
                goods.setGoodsUpdateTime(date);
                goods.setGoodsUpdateId(id);
                if(files != null){
                    StringBuffer stringBuffer = new StringBuffer("");
                    for (int i = 0; i < files.size(); i++) {
                        String path = this.upload2PNG(i+"_"+Utils.idTimer.format(date), "xhm_file/goods/"+goods.getId(),files.get(i) );
                        stringBuffer.append(path+",");
                    }
                   goods.setGoodsImg(stringBuffer.substring(0, stringBuffer.length()-1));
                }else{
                    goods.setGoodsImg("");
                }
                if (video !=null){
                    String path = this.upload(Utils.idTimer.format(date), "xhm_file/goods/"+goods.getId(),video );
                    goods.setGoodsVideo(path);
                }else {
                    goods.setGoodsVideo("");
                }
                cmsGoodsService.addOrUpdate(goods);
                msg.setMsg("添加成功");
                msg.setResult(goods);
                logger.info("add{ goods: {} }", goods.toString());
            }else{
                msg.setRecode(1);
                msg.setMsg("产品名称已存在");
            }

        }catch (Exception e){
            msg = Message.error();
            e.printStackTrace();
        }
        return msg;
    }

    /**
     *
     * @param goodsName
     * @param goodsIsShelf
     * @param startTime
     * @param endTime
     * @param start
     * @param limit
     * @return
     */
    @RequestMapping(value = "sel",method = RequestMethod.POST )
    public Message sel(@RequestParam String goodsName, @RequestParam Integer goodsIsShelf,
                       @RequestParam String startTime, @RequestParam String endTime,
                       @RequestParam(name="start") Integer start, @RequestParam(name="limit") Integer limit) {
        msg = Message.success();
        Map<String, Object> map = cmsGoodsService.findGoodsList(start, limit, goodsName, goodsIsShelf, startTime, endTime);
        map.put("fileUrl", this.getFileUrl());
        msg.setResult(map);
        msg.setMsg("查询成功");
        logger.info("sel{ start: {} - limit: {} }", start, limit);
        return msg;
    }

    @RequestMapping(value = "upd", method = RequestMethod.POST )
    public Message upd(@RequestParam String goodsId,@RequestParam String goodsName, @RequestParam String goodsDescribe, @RequestParam List<MultipartFile> files,@RequestParam(required = false) MultipartFile video,
                       @RequestParam double goodsPrice, @RequestParam double goodsCurrentPrice, @RequestParam String goodsDetails, @RequestParam String cid){
        try {
            msg = Message.success();
            Goods goods = cmsGoodsService.checkGoodsName(goodsName,goodsId);
            if (goods==null){
                goods = (Goods) daoUtils.getById("Goods",goodsId);
                Date date = new Date();
                goods.setGoodsName(goodsName);
                goods.setGoodsDescribe(goodsDescribe);
                goods.setGoodsPrice(goodsPrice);
                goods.setGoodsCurrentPrice(goodsCurrentPrice);
                goods.setGoodsDetails(goodsDetails);
                goods.setGoodsUpdateTime(date);
                goods.setGoodsUpdateId(cid);
                if(!files.isEmpty()){
                    StringBuffer stringBuffer = new StringBuffer("");
                    for (int i = 0; i < files.size(); i++) {
                        String path = this.upload2Filse(i+"_"+Utils.idTimer.format(date), "xhm_file/goods/"+goods.getId(),files.get(i) );
                        stringBuffer.append(path+",");
                    }
                    goods.setGoodsImg(stringBuffer.substring(0, stringBuffer.length()-1));
                }
                if (video!=null){
                    String path = this.upload(Utils.idTimer.format(date), "xhm_file/goods/"+goods.getId(),video );
                    goods.setGoodsVideo(path);
                }
                cmsGoodsService.update(goods);
                msg.setMsg("修改成功");
                msg.setResult(goods);
                logger.info("upd{ goods: {} }", goods.toString());
            }else{
                msg.setRecode(1);
                msg.setMsg("产品名称已存在");
            }

        }catch (Exception e){
            msg = Message.error();
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping(value = "updShelf", method = RequestMethod.POST )
    public Message updShelf(String id) {
        try {
            msg = Message.success();
            Goods goods = (Goods) daoUtils.getById("Goods", id);
            if (goods != null) {
                msg.setRecode(0);
                msg.setResult(cmsGoodsService.updShelf(goods));
            } else {
                msg.setRecode(1);
                msg.setMsg("产品不存在");
            }
        } catch (Exception e) {
            msg = Message.error();
            logger.error("异常信息");
        }
        return msg;
    }





}
