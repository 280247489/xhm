package com.memory.app.controller;

import com.memory.app.service.TopicsService;
import com.memory.common.utils.Result;
import com.memory.common.utils.ResultUtil;
import com.memory.entity.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author INS6+
 * @date 2019/7/10 14:07
 */

@RestController
@RequestMapping("topics")
public class TopicsController {

    private final static Logger log = LoggerFactory.getLogger(TopicsController.class);

    @Autowired
    private TopicsService topicsService;


    @RequestMapping("add")
    public Result createTopics(@ModelAttribute com.memory.entity.model.Topics topics){
        Result result = new Result();
        try {

            result = ResultUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            log.error("route",e.getMessage());
        }
        return result;
    }


    @RequestMapping("list")
    public Result list(@RequestParam String topicName){
        Result result = new Result();
        try {
            List<Topics> list =topicsService.queryTopicsByTopicName(topicName);
            result = ResultUtil.success(list);
        }catch (Exception e){
            e.printStackTrace();
            log.error("topics/list",e.getMessage());
        }
        return result;
    }





    /**
     * 热门话题
     * @return
     */
    @RequestMapping("hotList")
    public Result hotList(@RequestParam Integer page,@RequestParam Integer limit){
        Result result = new Result();
        try {
            List<Topics> list =topicsService.queryHotTopics(page,limit);
            result = ResultUtil.success(list);
        }catch (Exception e){
            e.printStackTrace();
            log.error("hotList",e.getMessage());
        }
        return result;
    }

    /**
     * 查看话题详情
     * @param id
     * @return
     */
    @RequestMapping("getTopicsById")
    public Result getTopicsById(@RequestParam String id){
        Result result = new Result();
        try {

            result = ResultUtil.success(topicsService.getTopicsById(id));
        }catch (Exception e){
            e.printStackTrace();
            log.error("getTopicsById",e.getMessage());
        }
        return result;
    }




}
