package com.memory.cms.controller;

import com.memory.cms.service.CmsTopicsService;
import com.memory.common.utils.PageResult;
import com.memory.common.utils.Result;
import com.memory.common.utils.ResultUtil;
import com.memory.common.utils.Utils;
import com.memory.entity.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author INS6+
 * @date 2019/7/1 19:34
 */
@RestController
@RequestMapping("cmsTopics")
public class CmsTopicsController {
    private final static Logger log = LoggerFactory.getLogger(CmsTopicsController.class);

    @Autowired
    private CmsTopicsService cmsTopicsService;

    @RequestMapping("add")
    public Result add(@ModelAttribute com.memory.entity.model.Topics topics){
        Result result = new Result();
        try {
            List<Topics> topicsList = cmsTopicsService.queryTopicsByTopicName(topics.getTopicName());

            if(Utils.isNotNull(topicsList)){
                result = ResultUtil.error(-1,"话题已存在，不能重复创建！");
            }else {

                Topics topicsSave = new Topics();
                topicsSave.setId(Utils.getShortUUID());
                topicsSave.setTopicName(topics.getTopicName());
                topicsSave.setArticleTypeId(topics.getArticleTypeId());
                topicsSave.setTopicSum(0);
                topicsSave.setTopicCreateUserId(topics.getTopicCreateUserId());
                topicsSave.setTopicCreateUser(topics.getTopicCreateUser());
                topicsSave.setTopicSort(topics.getTopicSort());
                topicsSave.setTopicStatus(0);
                topicsSave.setTopicCreateTime(new Date());
                Topics returnTopics = cmsTopicsService.add(topicsSave);
                result = ResultUtil.success(returnTopics);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("cmsTopics/add",e.getMessage());
        }
        return result;
    }


    @RequestMapping("list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                       @RequestParam String articleType,@RequestParam String sortType,@RequestParam Integer topicStatus,@RequestParam String topicName,
                       @RequestParam String beginTime,@RequestParam String endTime){
        Result result = new Result();
        try {
            int pageIndex = page+1;
            int limit = size;
            List<Topics> list =cmsTopicsService.queryTopicsByQue(pageIndex,limit,articleType,sortType,topicStatus,topicName,beginTime,endTime);
            int totalElements =cmsTopicsService.queryTopicsCountByQue(articleType,sortType,topicStatus,topicName,beginTime,endTime);
            PageResult pageResult = PageResult.getPageResult(page, size, list, totalElements);
            result = ResultUtil.success(pageResult);
        }catch (Exception e){
            e.printStackTrace();
            log.error("cmsTopics/list",e.getMessage());
        }
        return result;
    }


    @RequestMapping("delete")
    public Result delete(@ModelAttribute com.memory.entity.model.Topics topics){
        Result result = new Result();
        try {
            Topics topics1 = cmsTopicsService.getTopicsById(topics.getId());
            if(Utils.isNotNull(topics1)){
                if(topics1.getTopicStatus() == 0){
                    cmsTopicsService.deleteTopics(topics.getId());
                    result = ResultUtil.success();
                }else {
                    result = ResultUtil.error(-1,"话题已被启用，不可删除！");
                }

            }else {
                result = ResultUtil.error(-1,"非法话题");
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("cmsTopics/delete",e.getMessage());
        }
        return result;
    }

    @RequestMapping("changeStatus")
    public Result changeStatus(@ModelAttribute com.memory.entity.model.Topics topics){
        Result result = new Result();
        try {
            Topics topics1 = cmsTopicsService.getTopicsById(topics.getId());

            if(Utils.isNotNull(topics1)){
                if(topics1.getTopicStatus() == 0){
                    topics1.setTopicStatus(1);
                    Topics topics2=  cmsTopicsService.update(topics1);
                    result = ResultUtil.success(topics2);
                }else {
                    topics1.setTopicStatus(0);
                    Topics topics2=  cmsTopicsService.update(topics1);
                    result = ResultUtil.success(topics2);
                   // result = ResultUtil.error(-1,"话题已被启用，不可变更！");
                }
            }else {
                result = ResultUtil.error(-1,"非法话题");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("cmsTopics/changeStatus",e.getMessage());
        }
        return result;
    }







}
