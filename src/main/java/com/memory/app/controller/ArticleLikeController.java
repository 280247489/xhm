package com.memory.app.controller;

import com.memory.app.service.ArticleLikeService;
import com.memory.common.controller.BaseController;
import com.memory.common.utils.Result;
import com.memory.common.utils.ResultUtil;
import com.memory.common.utils.Utils;
import com.memory.entity.ArticleLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author INS6+
 * @date 2019/7/24 19:17
 */
@RestController
@RequestMapping("articleLike")
public class ArticleLikeController  extends BaseController {

    @Autowired
    private ArticleLikeService articleLikeService;

    @RequestMapping("like")
    public Result addLike(@RequestParam  String userId,@RequestParam String articleId){
        Result result = new Result();
        try {
            ArticleLike like = articleLikeService.getArticleLike(userId,articleId);
            if(Utils.isNotNull(like)){
                int likeStatus = 0;
                likeStatus = like.getLikeStatus();

                if(likeStatus == 0){
                    like.setLikeStatus(1);
                }else {
                    like.setLikeStatus(0);
                }
            }else {
                 like = new ArticleLike();
                like.setId(Utils.getShortUUID());
                like.setUserId(userId);
                like.setArticleId(articleId);
                like.setLikeStatus(1);
                like.setCreateTime(new Date());

            }
            ArticleLike resultLike = articleLikeService.updateArticleLike(like);
            result = ResultUtil.success(resultLike);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    @RequestMapping("likeList")
    public Result likeList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,@RequestParam  String userId){
        Result result = new Result();
        try {
            int pageIndex = page +1;
            List<com.memory.entity.model.ArticleLike> list = articleLikeService.queryArticleLikeByQue(pageIndex,size,userId);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("fileUrl", this.getFileUrl());
            map.put("data",list);
            result = ResultUtil.success(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }





}
