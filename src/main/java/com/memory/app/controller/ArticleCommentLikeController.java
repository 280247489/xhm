package com.memory.app.controller;

/**
 * @author INS6+
 * @date 2019/8/2 15:16
 */

import com.memory.app.service.ArticleCommentLikeService;
import com.memory.common.utils.Result;
import com.memory.common.utils.ResultUtil;
import com.memory.common.utils.Utils;
import com.memory.entity.ArticleCommentLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 评论点赞
 */

@RestController
@RequestMapping(value = "articleCommentLike")
public class ArticleCommentLikeController {

    @Autowired
    private ArticleCommentLikeService articleCommentLikeService;


    /**
     * 文章的点赞和取消点赞
     * @param userId
     * @param commentId
     * @return
     */
    @RequestMapping("isLike")
    public Result isLike(@RequestParam String userId,@RequestParam String commentId){
        Result result = new Result();
        try {
            ArticleCommentLike like =articleCommentLikeService.getArticleCommentLikeByUserIdAndCommentId(userId, commentId);
            if(Utils.isNotNull(like)){
                int oldType = like.getIsLike();
                //0点赞 1 取消点赞
                if(oldType == 0){
                    like.setIsLike(1);
                }else {
                    like.setIsLike(0);
                }
                articleCommentLikeService.update(like);
            }else {
                 like = new ArticleCommentLike();
                like.setId(Utils.getShortUUID());
                like.setIsLike(0);
                like.setUserId(userId);
                like.setCommentId(commentId);
                like.setCreateTime(new Date());
                articleCommentLikeService.add(like);

            }
            result = ResultUtil.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }







}
