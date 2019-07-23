package com.memory.app.controller;

import com.memory.app.service.UserCollectionService;
import com.memory.app.service.UserService;
import com.memory.common.utils.PageResult;
import com.memory.common.utils.Result;
import com.memory.common.utils.ResultUtil;
import com.memory.common.utils.Utils;
import com.memory.entity.User;
import com.memory.entity.UserCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;

/**
 * @author INS6+
 * @date 2019/7/23 19:39
 */

@RestController
@RequestMapping("userCollection")
public class UserCollectionController {

    private final static Logger log = LoggerFactory.getLogger(UserCollectionController.class);

    @Autowired
    private UserCollectionService userCollectionService;

    @Autowired
    private UserService userService;

    //添加关注
    @RequestMapping("addCollection")
    public Result addCollection(@RequestParam String userId,@RequestParam String attentionUserId){
        Result result = new Result();
        try {
            int sum = userCollectionService.getUserCollectionByIdAndAttentionUserId(userId,attentionUserId);
            if(sum == 0){
                UserCollection userCollection = new UserCollection();
                userCollection.setId(Utils.getShortUUID());
                userCollection.setCollectionUserId(userId);
                userCollection.setAttentionUserId(attentionUserId);
                userCollection.setIsFollow(1);
                userCollection.setCreateTime(new Date());
                UserCollection resultUserCo = userCollectionService.add(userCollection);


                result = ResultUtil.success(resultUserCo);
            }else {
                result = ResultUtil.error(-1,"该用户已关注过，不可重复关注");
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("addCollection",e.getMessage());
        }
        return result;
    }

    private void syncUserFollowAndFans(@RequestParam String userId, @RequestParam String attentionUserId) {
        //用户自己
        User myself =userService.getUserById(userId);
        int getFollowCount = userCollectionService.queryUserCollectionCountByQue(userId);
        //关注数增加
        myself.setUserFollow(getFollowCount);

        //被关注用户
        User there = userService.getUserById(attentionUserId);
        int getFansCount = userCollectionService.queryUserCollectionFansByQue(userId);
        //粉丝数增加
        there.setUserFans(getFansCount);
        //事务 更新数据库
        userService.syncUserFollowAndFans(myself,there);
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

            List<UserCollection> list = userCollectionService.queryUserCollectionListByQue(pageIndex,pageLimit,userId);
            int totalElements = userCollectionService.queryUserCollectionCountByQue(userId);
            PageResult pageResult = PageResult.getPageResult(page, pageLimit, list, totalElements);
            return ResultUtil.success(pageResult);
        }catch (Exception e){
            e.printStackTrace();
            log.error("myCollectionList",e.getMessage());
        }
        return result;
    }

    //查询关注详情
    @RequestMapping("collectionDetail")
    public Result method(){
        Result result = new Result();
        try {

            result = ResultUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            log.error("route",e.getMessage());
        }
        return result;
    }



}
