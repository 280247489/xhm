package com.memory.cms.controller;

import com.memory.cms.service.CmsArticleService;
import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import com.memory.common.utils.Utils;
import com.memory.entity.Article;
import com.memory.entity.ArticleLike;
import com.memory.entity.model.ArticleModel;
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
@RequestMapping("cmsArticle")
@RestController
public class CmsArticleController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(CmsArticleController.class);
    @Autowired
    private CmsArticleService cmsArticleService;

    /**
     * 查询文章
     * @param tid
     * @param online
     * @param check
     * @param del
     * @param start
     * @param limit
     * @return
     */
    @RequestMapping("sel")
    public Message sel(@RequestParam(name="userName") String userName,
                       @RequestParam(name="tid") String tid, @RequestParam(name="online") Integer online,
                       @RequestParam(name="check") Integer check, @RequestParam(name="del") Integer del,
                       @RequestParam(name="startTime") String startTime, @RequestParam(name="endTime") String endTime,
                       @RequestParam(name="start") Integer start, @RequestParam(name="limit") Integer limit) {
        msg = Message.success();
        Map<String, Object> map = new HashMap<>();
        map.put("fileUrl", this.getFileUrl());
        map.put("data", cmsArticleService.sel(userName, tid, online, check, del, startTime, endTime, start, limit));
        msg.setData(map);
        msg.setMsg("查询成功");
        logger.info("sel{ start: {} - limit: {} }", start, limit);
        return msg;
    }

    /**
     * 查询未审核文章
     * @param tid
     * @param start
     * @param limit
     * @return
     */
    @RequestMapping("selcheck")
    public Message sel(@RequestParam(name="tid") String tid, @RequestParam(name="start") Integer start, @RequestParam(name="limit") Integer limit) {
        msg = Message.success();
        Map<String, Object> map = new HashMap<>();
        map.put("fileUrl", this.getFileUrl());
        map.put("data", cmsArticleService.selCheck(tid, start, limit));
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
        Article article = cmsArticleService.selById(aid);
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
     * 审核文章
     * @param aid
     * @return
     */
    @RequestMapping("check")
    public Message check(@RequestParam(name="aid") String aid, @RequestParam(name="check") Integer check) {
        msg = Message.success();
        Article article = cmsArticleService.check(aid, check);
        if(article != null){
            msg.setMsg("操作成功");
        }else{
            msg.setMsg("ID不存在");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("fileUrl", this.getFileUrl());
        map.put("obj", article);
        msg.setData(map);
        return msg;
    }

    /**
     * 上下架文章
     * @param aid
     * @return
     */
    @RequestMapping("online")
    public Message online(@RequestParam(name="aid") String aid) {
        msg = Message.success();
        Article article = cmsArticleService.online(aid);
        if(article != null){
            msg.setMsg("操作成功");
        }else{
            msg.setMsg("ID不存在");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("fileUrl", this.getFileUrl());
        map.put("obj", article);
        msg.setData(map);
        return msg;
    }

    /**
     * 删除文章
     * @param aid
     * @return
     */
    @RequestMapping("del")
    public Message del(@RequestParam(name="aid") String aid) {
        msg = Message.success();
        Article article = cmsArticleService.del(aid);
        if(article != null){
            msg.setMsg("删除成功");
        }else{
            msg.setMsg("失败");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("fileUrl", this.getFileUrl());
        map.put("obj", article);
        msg.setData(map);
        return msg;
    }

}
