package com.memory.app.controller;

import com.alibaba.fastjson.JSON;
import com.memory.app.service.ArticleLikeService;
import com.memory.app.service.UserCollectionService;
import com.memory.app.service.UserService;
import com.memory.common.controller.BaseController;
import com.memory.common.utils.Message;
import com.memory.common.utils.Result;
import com.memory.common.utils.ResultUtil;
import com.memory.common.utils.Utils;
import com.memory.common.video.VideoUtils;
import com.memory.entity.*;
import com.memory.entity.model.ArticleModel;
import com.memory.app.service.ArticleService;
import com.memory.redis.config.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.memory.app.redis.dic.RedisDic.*;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 14:16
 * @Description:
 */
@RestController
@RequestMapping("articleCon")
public class ArticleController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCollectionService userCollectionService;

    @Autowired
    private ArticleLikeService articleLikeService;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 查询文章
     * @param tid
     * @param start
     * @param limit
     * @return
     */
    @RequestMapping("sel")
    public Message sel(@RequestParam(name="tid") String tid, @RequestParam(name="start") Integer start, @RequestParam(name="limit") Integer limit) {
        msg = Message.success();
        Map<String, Object> map = new HashMap<>();
        List<User> userList = userService.getUserList();

        System.out.println("userList =="+ JSON.toJSONString(userList));

        Map<String,Object> resultMap = articleService.sel(tid, start, limit);
        List<Article> list = (List<Article>) resultMap.get("list");
        for (Article article : list) {
            for (User user : userList) {
                System.out.println("article userId = " +article.getArticleCreateUserId());
                System.out.println("user id = " + user.getId());
                if(article.getArticleCreateUserId().equals(user.getId())){
                    System.out.println("=============================================================");
                    System.out.println("= id ===" +user.getId());
                    System.out.println("= name = " +user.getUserName());
                    article.setArticleCreateUserName(user.getUserName());
                }
            }
        }
        resultMap.put("list",list);
        map.put("fileUrl", this.getFileUrl());
        map.put("data", resultMap);
        msg.setResult(map);
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
    public Message selById(@RequestParam(name="aid") String aid,@RequestParam String userId) {
        msg = Message.success();
        Boolean isCollection =false;
        Boolean isLike =false;
        Article article = articleService.selById(aid);

        //点赞数
        String managerViewTotalKey = articleLikes  + aid;
        Integer dz = (Utils.isNotNull(redisUtil.get(managerViewTotalKey)))?(Integer.valueOf(redisUtil.get(managerViewTotalKey).toString())):0;
        article.setArticleTotaolDz(dz);

        //用户是否点赞
        String key1 = userLike +userId;
        Object object = redisUtil.hget(key1,aid);
        if(Utils.isNotNull(object)&& object.toString().equals("1") ){
            isCollection =true;
        }

/*
        //收藏数
        String managerViewTotalKey2 = ARTICLEFOLLOW + aid;
        Integer gz = (Utils.isNotNull(redisUtil.get(managerViewTotalKey2)))?(Integer.valueOf(redisUtil.get(managerViewTotalKey2).toString())):0;
        article.setArticleTotalLike(gz);

        //用户是否收藏
        String key2 = articleLikes +userId;
        Object object2 = redisUtil.hget(key2,aid);
        if(Utils.isNotNull(object2)&& object2.toString().equals("1") ){
            isLike =true;
        }
*/


        //转发数
        String managerViewTotalKey3 = articleForward + aid;
        Integer zf = (Utils.isNotNull(redisUtil.get(managerViewTotalKey3)))?(Integer.valueOf(redisUtil.get(managerViewTotalKey3).toString())):0;
        article.setArticleTotalShare(zf);



        String articleUserId ="";


        if(article != null){
            msg.setMsg("查询成功");
            articleUserId = article.getArticleCreateUserId();

        }else{
            msg.setMsg("失败");
        }
        System.out.println("articleUserId ===="+articleUserId);
        User user = userService.getUserById(articleUserId);



        ArticleLike articleLike = articleLikeService.getArticleLike(userId,aid);
        if(Utils.isNotNull(articleLike)){
            if(articleLike.getLikeStatus() == 1){
                isLike = true;
            }
        }



        Map<String, Object> map = new HashMap<>();
        map.put("fileUrl", this.getFileUrl());
        map.put("obj", article);
        map.put("user", user);
        map.put("isCollection", isCollection);
        map.put("isLike", isLike);

        msg.setResult(map);
        logger.info("selById{ aid: {} }", aid);
        return msg;
    }

    /**
     * 我的文章
     * @param uid
     * @param start
     * @param limit
     * @return
     */
    @RequestMapping("selByUserId")
    public Message selByUserId(@RequestParam(name="uid") String uid, @RequestParam(name="start") Integer start, @RequestParam(name="limit") Integer limit) {
        msg = Message.success();
        Map<String, Object> map = new HashMap<>();
        List<User> userList = userService.getUserList();



        Map<String,Object> resultMap = articleService.selByUserId(uid, start, limit);
        List<Article> list = (List<Article>) resultMap.get("list");
        for (Article article : list) {
            for (User user : userList) {
                System.out.println("article userId = " +article.getArticleCreateUserId());
                System.out.println("user id = " + user.getId());
                if(article.getArticleCreateUserId().equals(user.getId())){
                    System.out.println("=============================================================");
                    System.out.println("= id ===" +user.getId());
                    System.out.println("= name = " +user.getUserName());
                    article.setArticleCreateUserName(user.getUserName());
                    article.setArticleCreateUserLogo(user.getUserLogo());
                }
            }
        }
        resultMap.put("list",list);
        User user = userService.getUserById(uid);

        if(Utils.isNotNull(user)){

            Map<String,Integer> sumMap = this.queryUserFollowAndFans(uid);
            user.setUserFans(sumMap.get("fans"));
            user.setUserFollow(sumMap.get("follow"));
            String userLogo = user.getUserLogo();
            System.out.println("userLogo ===" + userLogo);
            if(userLogo.indexOf("http")<0){
                user.setUserLogo(this.getFileUrl() +userLogo);
            }


        }

        map.put("fileUrl", this.getFileUrl());
        map.put("data", resultMap);
        map.put("user", user);
        msg.setResult(map);
        msg.setMsg("查询成功");
        logger.info("selByUserId{ start: {} - limit: {} }", start, limit);
        return msg;
    }

    /**
     * 用户发布文章
     * @param model
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Message add(ArticleModel model) {
        msg = Message.success();
        Date date = new Date();
        model.getArticle().setId(Utils.getShortUUID());
        model.getArticle().setArticleCreateTime(date);
        model.getArticle().setArticleCheckTime(date);
        model.getArticle().setArticleLogo("");
        model.getArticle().setArticlePicture("");
        if(model.getPictures() != null){
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i = 0; i < model.getPictures().size(); i++) {
                String path = this.upload2PNG(i+"_"+Utils.idTimer.format(date), "xhm_file/article/"+model.getArticle().getId(), model.getPictures().get(i));
                stringBuffer.append(path+",");
                if(i==0){
                    model.getArticle().setArticleLogo(path);
                }
            }
            model.getArticle().setArticlePicture(stringBuffer.substring(0, stringBuffer.length()-1));
        }else{
            model.getArticle().setArticlePicture("");
        }
        //设置文章默认属性
        model.getArticle().setArticleOnline(0);
        model.getArticle().setArticleCheckYn(0);
        model.getArticle().setArticleDelYn(0);
        model.getArticle().setArticleTopYn(0);
        model.getArticle().setArticleTotalComment(0);
        Map<String, Object> map = new HashMap<>();
        map.put("fileUrl", this.getFileUrl());
        map.put("obj", articleService.add(model.getArticle()));
        msg.setResult(map);
        msg.setMsg("添加成功");
        logger.info("add{ article: {} }", model.getArticle().toString());
        return msg;
    }

    /**
     * 用户删除文章
     * @param aid
     * @param uid
     * @return
     */
    @RequestMapping("del")
    public Message del(@RequestParam(name="aid") String aid, @RequestParam(name="uid") String uid) {
        msg = Message.success();
        Article article = articleService.del(aid, uid);
        if(article != null){
            msg.setMsg("删除成功");
        }else{
            msg.setMsg("失败");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("fileUrl", this.getFileUrl());
        map.put("obj", article);
        msg.setResult(map);
        logger.info("del{ aid: {}, uid: {} }", aid, uid);
        return msg;
    }

    /**
     * 文章点赞
     * @param aid
     * @param uid
     * @return
     */
    @RequestMapping("like")
    public Message like(@RequestParam(name="aid") String aid, @RequestParam(name="uid") String uid) {
        msg = Message.success();

        ArticleLike articleLike = articleService.like(aid, uid);
        if(articleLike != null){
            msg.setMsg("成功");
        }else{
            msg.setMsg("失败");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("fileUrl", this.getFileUrl());
        map.put("obj", articleLike);
        msg.setResult(map);
        return msg;
    }

    //小程序图片、视频上传接口
    @RequestMapping(value = "picture")
    public Result uploadArticleFile(@RequestParam  MultipartFile file,@RequestParam String type,
                                    @RequestParam(value = "uuid",required = false) String uuid,@RequestParam(value = "num",required = false) Integer num,@RequestParam String os){
        Result result = new Result();
        String path ="";
        try {



            System.out.println("type =="+type);
            System.out.println("uuid =="+uuid);
            //没有传uuid 则生成uuid
            if(!Utils.isNotNull(uuid)){
                uuid = Utils.getShortUUID();
            }

            Date date = new Date();
            if("img".equals(type)){
                path = this.upload2PNG(num+"_"+Utils.idTimer.format(date), "xhm_file/article/"+uuid, file);
            }else {
                path = this.upload2MP4(num+"_"+Utils.idTimer.format(date), "xhm_file/article/"+uuid, file);

                //文件在服务器上的存储路径
                String realPath = this.getFilePath() + path;
                System.out.println("show final Img path = "+realPath.replace(".mp4",".png"));
                String finalPath = realPath.replace(".mp4",".png");
                //取视频某一帧作为封面图。
                VideoUtils.fetchFrame(realPath, realPath.replace(".mp4",".png"),os);

                //path = finalPath;

            }

            System.out.printf("file upload path ==="+path);

            Map<String,Object> returnMap = new HashMap <String, Object>();
            returnMap.put("uuid", uuid);
            returnMap.put("path", path);

            result = ResultUtil.success(returnMap);

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    //articleLabel 暂时用次字段存储文章类型 mp4/img
    @RequestMapping(value = "create")
    public Result create(@RequestParam String uuid,@RequestParam String typeId,@RequestParam String articleTitle,@RequestParam String articleContent,
                         @RequestParam String articleTopicsId,@RequestParam String articleTopics,@RequestParam String userId,
                         @RequestParam String articleLogo,@RequestParam String articlePicture,@RequestParam String articleLabel){
        Result result = new Result();
        try {


            Article article = new Article();
            article.setId(uuid);
            article.setTypeId(typeId);
            article.setArticleTitle(articleTitle);

            article.setArticlePicture(articlePicture);
            article.setArticleContent(articleContent);
            article.setArticleTopicsId(articleTopicsId);
            article.setArticleTopics(articleTopics);
            article.setArticleLabel("");
            article.setArticleKeyWords("");
            article.setArticleOnline(0);
            article.setArticleTotalView(0);
            article.setArticleTotalShare(0);
            article.setArticleTotalLike(0);
            article.setArticleCreateTime(new Date());
            article.setArticleCreateUserId(userId);
            article.setArticleCheckYn(0);
            article.setArticleCheckTime(new Date());
            article.setArticleCheckAdminId("");
            article.setArticleDelYn(0);
            article.setArticleTopYn(0);
            article.setArticleTotalComment(0);
            article.setArticleLabel(articleLabel);

            if(Utils.isNotNull(articleLabel) && articleLabel.equals("video")){
                String finalPath = articlePicture.replace(".mp4",".png");
                System.out.println("视频保存路径。。。" +finalPath );
                article.setArticleLogo(finalPath);
            }else{
                article.setArticleLogo(articleLogo);
            }

            Article article1 =articleService.add(article);

           result = ResultUtil.success(article1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("queryArticleByUserId")
    public Result queryArticleByUserId(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,@RequestParam String userId){
        Result result = new Result();
        try {
            int pageIndex = page+1;
            int limit = size;
            List<Article> articleList = articleService.queryArticleByUserId(userId,pageIndex,limit);

            Map<String,Object> map = new HashMap<String, Object>();
            map.put("fileUrl", this.getFileUrl());
            map.put("data",articleList);
            result = ResultUtil.success(map);
        }catch (Exception e){
            e.printStackTrace();
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

    @RequestMapping("search")
    public Result search(@RequestParam String searchWords){
        Result result = new Result();
        try {
           List<Article> list = articleService.search(searchWords);


            result = ResultUtil.success(list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("mainList")
    public Result mainList(@RequestParam String type,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        Result result = new Result();
        try {
            int pageIndex = page +1;
            int pageLimit = size;
            List<com.memory.entity.model.Article> list = articleService.queryArticleByQue(type,pageIndex,pageLimit);
            for (com.memory.entity.model.Article article : list) {
                String aid = article.getId();
                String managerViewTotalKey = articleLikes  + aid;
                Integer dz = (Utils.isNotNull(redisUtil.get(managerViewTotalKey)))?(Integer.valueOf(redisUtil.get(managerViewTotalKey).toString())):0;
                article.setArticleTotaolDz(dz);
            }


   /*         //点赞数
            String managerViewTotalKey = articleLikes  + aid;
            Integer dz = (Utils.isNotNull(redisUtil.get(managerViewTotalKey)))?(Integer.valueOf(redisUtil.get(managerViewTotalKey).toString())):0;
            article.setArticleTotaolDz(dz);*/



            Map<String,Object> map = new HashMap<String, Object>();
            map.put("fileUrl", this.getFileUrl());
            map.put("data",list);

            result = ResultUtil.success(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询某一话题的热门文章
     * @return
     */
    @RequestMapping("queryHotTopicsList")
    public Result queryHotTopicsList(@RequestParam String topicsId,@RequestParam Integer page,@RequestParam Integer size ){
        Result result = new Result();
        try {
            int pageIndex = page +1;
            int pageLimit = size;
            List<com.memory.entity.model.Article> list = articleService.queryHotTopoicsArticleByQue(topicsId,pageIndex,pageLimit);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("fileUrl", this.getFileUrl());
            map.put("data",list);
            result = ResultUtil.success(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询某一话题的最新文章
     * @return
     */
    @RequestMapping("queryNowsTopicsList")
    public Result queryNowsTopicsList(@RequestParam String topicsId,@RequestParam Integer page,@RequestParam Integer size ){
        Result result = new Result();
        try {
            int pageIndex = page +1;
            int pageLimit = size;
            List<com.memory.entity.model.Article> list = articleService.queryNewsTopoicsArticleByQue(topicsId,pageIndex,pageLimit);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("fileUrl", this.getFileUrl());
            map.put("data",list);
            result = ResultUtil.success(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 设置文章分享次数
     * @param articleId
     * @return
     */
    @RequestMapping("share")
    public Result share(@RequestParam String articleId){
        Result result = new Result();
        try {
            Article article = articleService.getArticleById(articleId);
            if(Utils.isNotNull(article)){
                redisUtil.incr(articleForward+articleId,1);
            }
          //  articleService.update(article);
            result = ResultUtil.success();
        }catch (Exception e){
            e.printStackTrace();

        }
        return result;
    }







}
