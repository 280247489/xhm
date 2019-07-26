package com.memory.app.controller;

import com.memory.app.service.ArticleService;
import com.memory.app.service.UserCollectionService;
import com.memory.app.service.UserService;
import com.memory.common.controller.BaseController;
import com.memory.common.utils.PageResult;
import com.memory.common.utils.Result;
import com.memory.common.utils.ResultUtil;
import com.memory.common.utils.Utils;
import com.memory.entity.Article;
import com.memory.entity.User;
import com.memory.entity.UserCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @date 2019/7/23 19:39
 */

@RestController
@RequestMapping("userCollection")
public class UserCollectionController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(UserCollectionController.class);

    @Autowired
    private UserCollectionService userCollectionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    //添加关注
    @RequestMapping("addCollection")
    public Result addCollection(@RequestParam String userId,@RequestParam String attentionUserId){
        Result result = new Result();
        try {
            UserCollection userCollectionShow = userCollectionService.getUserCollectionByCollectionUserIdAndAttentionUserId(userId,attentionUserId);

            if(Utils.isNotNull(userCollectionShow)){
                if(userCollectionShow.getIsFollow() == 0){
                    userCollectionShow.setIsFollow(1);
                    UserCollection resultUserCo = userCollectionService.add(userCollectionShow);

                    result = ResultUtil.success(resultUserCo);
                }else {
                    result = ResultUtil.error(-1,"该用户已关注过，不可重复关注");
                }
            }else {
                UserCollection userCollection = new UserCollection();
                userCollection.setId(Utils.getShortUUID());
                userCollection.setCollectionUserId(userId);
                userCollection.setAttentionUserId(attentionUserId);
                userCollection.setIsFollow(1);
                userCollection.setCreateTime(new Date());
                UserCollection resultUserCo = userCollectionService.add(userCollection);


                result = ResultUtil.success(resultUserCo);
            }



        }catch (Exception e){
            e.printStackTrace();
            log.error("addCollection",e.getMessage());
        }
        return result;
    }

    private  Map<String,Integer> queryUserFollowAndFans(@RequestParam String userId) {
        //用户自己
       // User myself =userService.getUserById(userId);
        int getFollowCount = userCollectionService.queryUserCollectionCountByQue(userId);
        //关注数增加
      //  myself.setUserFollow(getFollowCount);

        //被关注用户
       // User there = userService.getUserById(attentionUserId);
        int getFansCount = userCollectionService.queryUserCollectionFansByQue(userId);
        //粉丝数增加
        //there.setUserFans(getFansCount);
        //事务 更新数据库
      //  userService.syncUserFollowAndFans(myself,there);
        Map<String,Integer> returnMap = new HashMap<String,Integer>();
        returnMap.put("follow",getFollowCount);
        returnMap.put("fans",getFansCount);
        return returnMap;
    }

    //取消关注
    @RequestMapping("removeCollection")
    public Result removeCollection(@RequestParam String userId,@RequestParam String attentionUserId){
        Result result = new Result();
        try {
            UserCollection userCollection = userCollectionService.getUserCollectionByCollectionUserIdAndAttentionUserId(userId,attentionUserId);
            if(Utils.isNotNull(userCollection)){

                int isFollow = userCollection.getIsFollow();
                //防止重复取消关注
                if(isFollow == 1){
                    userCollection.setIsFollow(0);
                    userCollectionService.update(userCollection);
                }
                result = ResultUtil.success();

            }else{
                result = ResultUtil.error(-1,"找不到此用户");
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("removeCollection",e.getMessage());
        }
        return result;
    }

    //查找我的关注列表
    @RequestMapping("myCollectionList")
    public Result myCollectionList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer pageLimit,@RequestParam String userId){
        Result result = new Result();
        try {

            int pageIndex = page+1;

            List<com.memory.entity.model.UserCollection> list = userCollectionService.queryUserCollectionListByQue(pageIndex,pageLimit,userId);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("fileUrl", this.getFileUrl());
            map.put("data",list);
            result = ResultUtil.success(map);
        }catch (Exception e){
            e.printStackTrace();
            log.error("myCollectionList",e.getMessage());
        }
        return result;
    }

    //查找我的粉丝列表
    @RequestMapping("myFansList")
    public Result myFansList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer pageLimit,@RequestParam String userId){
        Result result = new Result();
        try {

            int pageIndex = page+1;

            List<com.memory.entity.model.UserCollection> list = userCollectionService.queryUserFansListByQue(pageIndex,pageLimit,userId);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("fileUrl", this.getFileUrl());
            map.put("data",list);
            result = ResultUtil.success(map);
        }catch (Exception e){
            e.printStackTrace();
            log.error("myCollectionList",e.getMessage());
        }
        return result;
    }




    //查询关注详情
    @RequestMapping("collectionDetail")
    public Result collectionDetail(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,@RequestParam String userId){
        Result result = new Result();
        Map<String,Object> resultMap = new HashMap<String, Object>();
        try {
            User user = userService.getUserById(userId);
            if(Utils.isNotNull(user)){
                Map<String,Integer> mapper = this.queryUserFollowAndFans(userId);
                user.setUserFollow(mapper.get("follow"));
                user.setUserFans(mapper.get("fans"));

                int pageIndex = page+1;
                int limit = size;
                List<Article> articleList = articleService.queryArticleByUserId(userId,pageIndex,limit);
                resultMap.put("fileUrl", this.getFileUrl());
                resultMap.put("userInfo",user);
                resultMap.put("articleList",articleList);
                result = ResultUtil.success(resultMap);

            }else{
                result = ResultUtil.error(-1,"非法用户");
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("route",e.getMessage());
        }
        return result;
    }



}
