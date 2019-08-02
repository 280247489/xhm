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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 文章点赞表
 */
@RestController
@RequestMapping("userCon")
public class UserFollowController {

    @Autowired
    private UserFollowService userFollowService;

    @Autowired
    private ArticleService articleService;


    /**
     * 文章点赞和取消点赞
     * @return
     */
    @RequestMapping("like")
    public Result like(@RequestParam String userId,@RequestParam String articleId){
        Result result = new Result();
        try {
            UserFollow userFollow =userFollowService.getUserFollowByArticleIdAndFollowUserId(articleId,userId);
            if(Utils.isNotNull(userFollow)){
                // 0 点赞 1 取消点赞
                    int oldType = userFollow.getIsFollow();
                    if(oldType == 0){
                        userFollow.setIsFollow(1);
                    }else {
                        userFollow.setIsFollow(0);
                    }
                userFollowService.update(userFollow);
            }else {
                userFollow = new UserFollow();
                userFollow.setId(Utils.getShortUUID());
                userFollow.setArticleId(articleId);
                userFollow.setFollowUserId(userId);
                userFollow.setIsFollow(0);
                userFollow.setCreateTime(new Date());
                userFollowService.add(userFollow);
            }

            //从表里查询出article数量并存入article 表
            Article article =articleService.getArticleById(articleId);

            int likeCount = userFollowService.getUserFollowByArticleId(articleId);
            //点赞数量
            article.setArticleTotaolDz(likeCount);
            articleService.update(article);

            result = ResultUtil.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询某个文章的点赞数
     * @param articleId
     * @return
     */
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


    /**
     * 查询某人是否赞过某篇文章
     * @param articleId
     * @param userId
     * @return
     */
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







}
