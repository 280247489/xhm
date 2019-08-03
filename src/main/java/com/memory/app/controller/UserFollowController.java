package com.memory.app.controller;

/**
 * @author INS6+
 * @date 2019/8/2 15:37
 */

import com.memory.app.service.ArticleService;
import com.memory.app.service.UserFollowService;
import com.memory.common.utils.Result;
import com.memory.common.utils.ResultUtil;
import com.memory.common.utils.Utils;
import com.memory.entity.Article;
import com.memory.entity.UserFollow;
import com.memory.redis.config.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.memory.app.redis.dic.RedisDic.articleLikes;
import static com.memory.app.redis.dic.RedisDic.userLike;

/**
 * 文章点赞表
 */
@RestController
@RequestMapping("userFollowCon")
public class UserFollowController {

    @Autowired
    private UserFollowService userFollowService;

    @Autowired
    private ArticleService articleService;


    @Autowired
    private RedisUtil redisUtil;


    /**
     * 文章点赞和取消点赞
     * @return
     */
    @RequestMapping("like")
    public Result like(@RequestParam String userId,@RequestParam String articleId){
        Result result = new Result();
        String articleLike = articleLikes + articleId;
        String articleLikeDetail = userLike + userId;
        try {
            //判断用户是否存在点赞数据
            Object isLike =  redisUtil.hget(articleLikeDetail,articleId);
            if (isLike != null){
                Integer like = Integer.valueOf(isLike.toString());
                if (like==0){
                    redisUtil.incr(articleLike,1);
                    redisUtil.hset(articleLikeDetail,articleId,"1");
                }else{
                    redisUtil.decr(articleLike,1);
                    redisUtil.hset(articleLikeDetail,articleId,"0");
                }
            }else{
                redisUtil.incr(articleLike,1);
                redisUtil.hset(articleLikeDetail,articleId,"1");
            }

            result = ResultUtil.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

   /* *//**
     * 查询某个文章的点赞数
     * @param articleId
     * @return
     *//*
    @RequestMapping("queryLikeCount")
    public Result queryLikeCount(@RequestParam String articleId){
        Result result = new Result();
        try {
            //查询点赞数量
            int count = userFollowService.getUserFollowByArticleId(articleId);

            result = ResultUtil.success(count);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    *//**
     * 查询某人是否赞过某篇文章
     * @param articleId
     * @param userId
     * @return
     *//*
    @RequestMapping("isLike")
    public Result isLike(@RequestParam String articleId,@RequestParam String userId){
        Result result = new Result();
        try {

            boolean isLike = false;
            UserFollow userFollow = userFollowService.getUserFollowByArticleIdAndFollowUserId(articleId,userId);
            if(Utils.isNotNull(userFollow) && userFollow.getIsFollow() == 0){
                isLike = true;
            }
            result = ResultUtil.success(isLike);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

*/





}
