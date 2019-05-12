package com.memory.xhm.controller;

import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import com.memory.common.utils.Utils;
import com.memory.xhm.entity.Article;
import com.memory.xhm.entity.model.ArticleModel;
import com.memory.xhm.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 14:16
 * @Description:
 */
@RestController
@RequestMapping("articleCon")
public class ArticleController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    private ArticleService articleService;

    /**
     * 查询文章
     * @param start
     * @param limit
     * @return
     */
    @RequestMapping("sel")
    public Message sel(@RequestParam(name="start") Integer start, @RequestParam(name="limit") Integer limit) {
        msg = Message.success();
        msg.setData(articleService.sel(start, limit));
        msg.setMsg("查询成功");
        logger.info("sel{ start: {} - limit: {} }", start, limit);
        return msg;
    }

    @RequestMapping("selByUserId")
    public Message selByUserId(@RequestParam(name="userId") String userId, @RequestParam(name="start") Integer start, @RequestParam(name="limit") Integer limit) {
        msg = Message.success();
        msg.setData(articleService.selByUserId(userId, start, limit));
        msg.setMsg("查询成功");
        logger.info("selByUserId{ start: {} - limit: {} }", start, limit);
        return msg;
    }

    /**
     * 用户发布文章
     * @param model
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Message add(ArticleModel model) {
        msg = Message.success();
        Date date = new Date();
        model.getArticle().setId(Utils.getShortUUID());
        model.getArticle().setArticleCreateTime(date);
        model.getArticle().setArticleCheckTime(date);
        model.getArticle().setArticleLogo("");
        model.getArticle().setArticlePicture("");
        if(model.getPictures() != null){
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i = 0; i < model.getPictures().size(); i++) {
                stringBuffer.append(this.upload2PNG(i+"_"+Utils.idTimer.format(date), model.getArticle().getId(), model.getPictures().get(i))+",");
            }
            model.getArticle().setArticlePicture(stringBuffer.substring(0, stringBuffer.length()-1));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("fileUrl", this.getFileUrl());
        map.put("obj", articleService.add(model.getArticle()));
        msg.setData(map);
        msg.setMsg("添加成功");
        logger.info("add{ article: {} }", model.getArticle().toString());
        return msg;
    }
}
