package com.memory.app.controller;

/**
 * @author INS6+
 * @date 2019/8/2 15:16
 */

import com.memory.app.service.ArticleCommentLikeService;
import com.memory.common.utils.Result;
import com.memory.common.utils.ResultUtil;
import com.memory.redis.config.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.memory.app.redis.dic.RedisDic.articleCommentLike;
import static com.memory.app.redis.dic.RedisDic.articleCommentLikeDetail;

/**
 * 评论点赞
 */

@RestController
@RequestMapping(value = "articleCommentLike")
public class ArticleCommentLikeController {

    @Autowired
    private ArticleCommentLikeService articleCommentLikeService;


    @Autowired
    private RedisUtil redisUtil;
    /**
     * 文章的点赞和取消点赞
     * @param userId
     * @param commentId
     * @return
     */
    @RequestMapping("isLike")
    public Result isLike(@RequestParam String userId,@RequestParam String commentId){
        /**
         *     //文章评论的点赞数量
         *     //key article:comment:like:commentId
         *     //val count 评论点赞数
         *     public static final String articleCommentLike ="article:comment:like:";
         *
         *     //用户文章评论的点赞 是否
         *     //key article:comment:like:detail: userId
         *     //val:hashMap => key:commentId value:是否点赞 0/1
         *     public static final String articleCommentLikeDetail ="user:comment:like:detail:";
         */

        String articleLike = articleCommentLike + commentId;
        String articleLikeDetail = articleCommentLikeDetail + userId;
        Result result = new Result();
        try {
            //判断用户是否存在点赞数据
            Object isLike =  redisUtil.hget(articleLikeDetail,commentId);
            if (isLike != null){
                Integer like = Integer.valueOf(isLike.toString());
                if (like==0){
                    redisUtil.incr(articleLike,1);
                    redisUtil.hset(articleLikeDetail,commentId,"1");
                }else{
                    redisUtil.decr(articleLike,1);
                    redisUtil.hset(articleLikeDetail,commentId,"0");
                }
            }else{
                redisUtil.incr(articleLike,1);
                redisUtil.hset(articleLikeDetail,commentId,"1");
            }
            result = ResultUtil.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }







}
