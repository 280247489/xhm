package com.memory.app.controller;
import com.alibaba.fastjson.JSON;
import com.memory.app.service.ArticleCommentService;
import com.memory.app.service.ArticleService;
import com.memory.app.service.UserService;
import com.memory.cms.service.ArticleCommentCmsService;
import com.memory.common.utils.PageResult;
import com.memory.common.utils.Result;
import com.memory.common.utils.ResultUtil;
import com.memory.common.utils.Utils;
import com.memory.entity.Article;
import com.memory.entity.User;
import com.memory.entity.model.ArticleComment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author INS6+
 * @date 2019/5/23 17:03
 */
@RestController
@RequestMapping(value = "articleComment")
public class ArticleCommentController {

    private final static Logger log = LoggerFactory.getLogger(ArticleCommentController.class);

    @Autowired
    private ArticleCommentService articleCommentService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;




    /**
     * 添加评论
     * @param user_id
     * @param comment_parent_id
     * @param content
     * @return
     */
    @RequestMapping(value = "add")
    public  Result addAdminComment(@RequestParam("user_id") String user_id,@RequestParam String articleId,@RequestParam("comment_parent_id") String comment_parent_id,@RequestParam("content") String content ,@RequestParam("content_replace") String content_replace  ){
        Result result = new Result();
        try{
            Article article = articleService.getArticleById(articleId);
            if(article==null){
                return ResultUtil.error(-1,"非法文章！");
            }

            User user =userService.getUserById(user_id);
            if(user==null){
                return ResultUtil.error(-1,"非法用户！");
            }

            article.setArticleTotalComment(article.getArticleTotalComment()+1);


            System.out.println("content_replace == " + content_replace);
            com.memory.entity.ArticleComment parentArticleComment =  articleCommentService.getArticleCommentById(comment_parent_id);
            com.memory.entity.ArticleComment articleComment  = new com.memory.entity.ArticleComment();
            articleComment.setId(Utils.getShortUUID());
            articleComment.setUserId(user.getId());
            articleComment.setUserLogo(user.getUserLogo());
            articleComment.setUserName(user.getUserName());
            articleComment.setArticleId(parentArticleComment.getArticleId());
            articleComment.setCommentType(1);
            articleComment.setCommentRootId(parentArticleComment.getCommentRootId());
            articleComment.setCommentParentId(comment_parent_id);

            System.out.println("parentUserName = " + "@"+parentArticleComment.getUserName());
            articleComment.setCommentParentUserName("@"+parentArticleComment.getUserName());
            articleComment.setCommentParentContent(parentArticleComment.getCommentContentReplace());

            articleComment.setCommentContent(content);
            articleComment.setCommentCreateTime(new Date());
            articleComment.setCommentTotalLike(0);
            articleComment.setCommentContentReplace(content_replace);

            com.memory.entity.ArticleComment articleComment1 =   articleCommentService.addArticleComment(articleComment);
            articleService.update(article);
            result = ResultUtil.success(articleComment1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    //添加一级评论
    @RequestMapping("addFirstLevelComment")
    public Result addFirstLevelComment(@RequestParam String articleId,@RequestParam String userId,@RequestParam("content") String content ,@RequestParam("content_replace") String content_replace   ){
        Result result = new Result();
        try {

            User user =userService.getUserById(userId);
            if(user==null){
                return ResultUtil.error(-1,"非法用户！");
            }
            Article article = articleService.getArticleById(articleId);
            if(article==null){
                return ResultUtil.error(-1,"非法文章！");
            }
            article.setArticleTotalComment(article.getArticleTotalComment()+1);

            String uuid = Utils.getShortUUID();
            System.out.println("content_replace == " + content_replace);
            com.memory.entity.ArticleComment articleComment  = new com.memory.entity.ArticleComment();
            articleComment.setId(uuid);
            articleComment.setUserId(user.getId());
            articleComment.setUserLogo(user.getUserLogo());
            articleComment.setUserName(user.getUserName());
            articleComment.setArticleId(articleId);
            articleComment.setCommentType(0);
            articleComment.setCommentRootId(uuid);
            articleComment.setCommentParentId("");

            articleComment.setCommentParentUserName("");
            articleComment.setCommentParentContent("");
            articleComment.setCommentContent(content);
            articleComment.setCommentCreateTime(new Date());
            articleComment.setCommentTotalLike(0);
            articleComment.setCommentContentReplace(content_replace);
            articleService.update(article);
            com.memory.entity.ArticleComment articleComment1 =   articleCommentService.addArticleComment(articleComment);
            result = ResultUtil.success(articleComment1);

        }catch (Exception e){
            e.printStackTrace();
            log.error("addFirstLevelComment",e.getMessage());
        }
        return result;
    }






    @RequestMapping(value = "list")
    public Result queryArticleCommentByQue(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                                           @RequestParam("key_words") String key_words, @RequestParam("phone_number") String phone_number,
                                           @RequestParam("article_name") String article_name, @RequestParam("user_name") String user_name,
                                           @RequestParam("comment_type") Integer comment_type, @RequestParam("query_start_time") String query_start_time,
                                           @RequestParam("query_end_time") String query_end_time, @RequestParam("sort_role") Integer sort_role,
                                           @RequestParam("comment_root_id") String comment_root_id, @RequestParam(value = "id",required = false) String id,
                                           @RequestParam(value = "article_id",required = false) String article_id){

        int pageIndex = page+1;
        int limit = size;
        List<ArticleComment> list = articleCommentService.queryArticleCommentByQueHql( pageIndex, limit, key_words, phone_number,  article_name,  user_name,  comment_type,  query_start_time,  query_end_time,  sort_role,comment_root_id,id,article_id);
        int totalElements = articleCommentService.queryArticleCommentByQueHqlCount(  key_words, phone_number,  article_name,  user_name,  comment_type,  query_start_time,  query_end_time,  sort_role,comment_root_id,id,article_id);
        PageResult pageResult = PageResult.getPageResult(page, size, list, totalElements);
        return ResultUtil.success(pageResult);
    }



    @RequestMapping("listFirst")
    public Result listFirst(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam String articleId){
        Result result = new Result();
        try {
            int pageIndex = page+1;
            int limit = size;
            List<com.memory.entity.ArticleComment> list = articleCommentService.queryArticleCommentFirstByArticleId(pageIndex,limit,articleId);
            int totalElements = articleCommentService.queryArticleCommentFirstCountByArticleId(articleId);
            PageResult pageResult = PageResult.getPageResult(page, size, list, totalElements);
            return ResultUtil.success(pageResult);
        }catch (Exception e){
            e.printStackTrace();
            log.error("listFirst",e.getMessage());
        }
        return result;
    }


    @RequestMapping("listSecond")
    public Result secondFirst(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                           @RequestParam String commentRootId){
        Result result = new Result();
        try {
            int pageIndex = page+1;
            int limit = size;
            List<com.memory.entity.ArticleComment> list = articleCommentService.getArticleCommentSecondByArticleId(commentRootId,pageIndex,limit);
            int totalElements = articleCommentService.getArticleCommentSecondCountByArticleId(commentRootId);
            PageResult pageResult = PageResult.getPageResult(page, size, list, totalElements);
            return ResultUtil.success(pageResult);
        }catch (Exception e){
            e.printStackTrace();
            log.error("secondFirst",e.getMessage());
        }
        return result;
    }



    //查询文章详情
    @RequestMapping("getArticleCommentById")
    public Result getArticleCommentById(@RequestParam String articleCommentId){
        Result result = new Result();
        try {
            com.memory.entity.ArticleComment articleComment =articleCommentService.getArticleCommentById(articleCommentId);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("data",articleComment);
            map.put("fileUrl","");
            result = ResultUtil.success(map);
        }catch (Exception e){
            e.printStackTrace();
            log.error("getArticleCommentById",e.getMessage());
        }
        return result;
    }






    @RequestMapping(value = "remove")
    public Result remove(@RequestParam("comment_id") String comment_id){
        Result result = new Result();
        try{


            List<com.memory.entity.ArticleComment> removeList = new ArrayList<>();

            com.memory.entity.ArticleComment articleComment = articleCommentService.getArticleCommentById(comment_id);

            if(articleComment == null){
                return ResultUtil.error(-1,"非法id");
            }
            String comment_root_id = articleComment.getCommentRootId();


            if(comment_id.equals(comment_root_id)){
                articleCommentService.deleteArticleCommentByCommentRootId(comment_root_id);
            }else{
                Date comment_create_time = articleComment.getCommentCreateTime();
                List<com.memory.entity.ArticleComment> list = articleCommentService.queryArticleCommentList(comment_root_id,comment_create_time);
                System.out.println("query msg = " );
                System.out.println(JSON.toJSONString(list));

                if(list.size()<=100){
                    removeComment(list, comment_id,removeList);
                    removeList.add(articleComment);
                }else{
                 //   removeList.add(articleComment);
                    result = ResultUtil.error(-1,"递归删除长度大于100,禁止删除!");
                }

            }
           // System.out.println("remove list size ================== " +removeList.size());
          //  System.out.println("remove list = " + JSON.toJSONString(removeList));
            articleCommentService.deleteAll(removeList);
            result = ResultUtil.success("删除成功");

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }



    public List<com.memory.entity.ArticleComment> removeComment(List< com.memory.entity.ArticleComment> list, String parentId,  List<com.memory.entity.ArticleComment> removeList){

        if(list.size()<=100){
            if(list.size() == 1){
                removeList.add(list.get(0));
            }else{
                for (int i = 0; i < list.size(); i++) {
                    com.memory.entity.ArticleComment dg_obj = list.get(i);
                    if(parentId.equals(dg_obj.getCommentParentId())){
                        removeList.add(dg_obj);
                        removeComment(list, dg_obj.getId(),removeList);
                    }
                }
            }


        }else{
            System.out.println("递归数据大于100");
        }
        return removeList;
    }

}
