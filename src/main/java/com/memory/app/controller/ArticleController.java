package com.memory.app.controller;

import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import com.memory.common.utils.Utils;
import com.memory.entity.Article;
import com.memory.entity.ArticleLike;
import com.memory.entity.model.ArticleModel;
import com.memory.app.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
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
     * @param tid
     * @param start
     * @param limit
     * @return
     */
    @RequestMapping("sel")
    public Message sel(@RequestParam(name="tid") String tid, @RequestParam(name="start") Integer start, @RequestParam(name="limit") Integer limit) {
        msg = Message.success();
        Map<String, Object> map = new HashMap<>();
        map.put("fileUrl", this.getFileUrl());
        map.put("data", articleService.sel(tid, start, limit));
        msg.setData(map);
        msg.setMsg("查询成功");
        logger.info("sel{ start: {} - limit: {} }", start, limit);
        return msg;
    }

    /**
     * 查询文章详情
     * @param aid
     * @return
     */
    @RequestMapping("selById")
    public Message selById(@RequestParam(name="aid") String aid) {
        msg = Message.success();
        Article article = articleService.selById(aid);
        if(article != null){
            msg.setMsg("查询成功");
        }else{
            msg.setMsg("失败");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("fileUrl", this.getFileUrl());
        map.put("obj", article);
        msg.setData(map);
        logger.info("selById{ aid: {} }", aid);
        return msg;
    }

    /**
     * 我的文章
     * @param uid
     * @param start
     * @param limit
     * @return
     */
    @RequestMapping("selByUserId")
    public Message selByUserId(@RequestParam(name="uid") String uid, @RequestParam(name="start") Integer start, @RequestParam(name="limit") Integer limit) {
        msg = Message.success();
        Map<String, Object> map = new HashMap<>();
        map.put("fileUrl", this.getFileUrl());
        map.put("data", articleService.selByUserId(uid, start, limit));
        msg.setData(map);
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

    /**
     * 用户删除文章
     * @param aid
     * @param uid
     * @return
     */
    @RequestMapping("del")
    public Message del(@RequestParam(name="aid") String aid, @RequestParam(name="uid") String uid) {
        msg = Message.success();
        Article article = articleService.del(aid, uid);
        if(article != null){
            msg.setMsg("删除成功");
        }else{
            msg.setMsg("失败");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("fileUrl", this.getFileUrl());
        map.put("obj", article);
        msg.setData(map);
        logger.info("del{ aid: {}, uid: {} }", aid, uid);
        return msg;
    }

    /**
     * 文章点赞
     * @param aid
     * @param uid
     * @return
     */
    @RequestMapping("like")
    public Message like(@RequestParam(name="aid") String aid, @RequestParam(name="uid") String uid) {
        msg = Message.success();

        ArticleLike articleLike = articleService.like(aid, uid);
        if(articleLike != null){
            msg.setMsg("成功");
        }else{
            msg.setMsg("失败");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("fileUrl", this.getFileUrl());
        map.put("obj", articleLike);
        msg.setData(map);
        return msg;
    }
}
