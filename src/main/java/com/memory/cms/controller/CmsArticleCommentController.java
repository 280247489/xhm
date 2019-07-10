package com.memory.cms.controller;

import com.alibaba.fastjson.JSON;
import com.memory.cms.service.ArticleCommentCmsService;
import com.memory.common.utils.PageResult;
import com.memory.common.utils.Result;
import com.memory.common.utils.ResultUtil;
import com.memory.common.utils.Utils;
import com.memory.entity.model.ArticleComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author INS6+
 * @date 2019/6/28 17:52
 */
@RestController
@RequestMapping("cmsArticleComment")
public class CmsArticleCommentController {


    @Autowired
    private ArticleCommentCmsService articleCommentCmsService;

 /*   @Autowired
    private SysAdminCmsService sysAdminCmsService;*/



    @RequestMapping(value = "list")
    public Result queryArticleCommentByQue(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                                           @RequestParam("key_words") String key_words,
                                           @RequestParam("article_title") String article_title, @RequestParam("user_name") String user_name,
                                           @RequestParam("comment_type") Integer comment_type, @RequestParam("query_start_time") String query_start_time,
                                           @RequestParam("query_end_time") String query_end_time, @RequestParam("sort_role") Integer sort_role,
                                           @RequestParam("comment_root_id") String comment_root_id, @RequestParam(value = "id",required = false) String id,
                                           @RequestParam(value = "article_id",required = false) String article_id){

        int pageIndex = page;
        int limit = size;
        List<ArticleComment> list = articleCommentCmsService.queryArticleCommentByQueHql( pageIndex, limit, key_words, "",  article_title,  user_name,  comment_type,  query_start_time,  query_end_time,  sort_role,comment_root_id,id,article_id);
        int totalElements = articleCommentCmsService.queryArticleCommentByQueHqlCount(  key_words, "",  article_title,  user_name,  comment_type,  query_start_time,  query_end_time,  sort_role,comment_root_id,id,article_id);
        PageResult pageResult = PageResult.getPageResult(page, size, list, totalElements);
        return ResultUtil.success(pageResult);
    }




    /**
     *
     * @param user_id
     * @param user_logo
     * @param user_name
     * @param comment_parent_id
     * @param content
     * @return
     */
    @RequestMapping(value = "add")
    public  Result addAdminComment(@RequestParam("user_id") String user_id,@RequestParam("user_logo") String user_logo,@RequestParam("user_name") String user_name,@RequestParam("comment_parent_id") String comment_parent_id,@RequestParam("content") String content ,@RequestParam("content_replace") String content_replace  ){
        Result result = new Result();
        try{

/*            SysAdmin sysAdmin =sysAdminCmsService.getSysAdminById(user_id);
            if(sysAdmin==null){
                return ResultUtil.error(-1,"非法用户！");
            }*/

            System.out.println("content_replace == " + content_replace);
            com.memory.entity.ArticleComment parentArticleComment =  articleCommentCmsService.getArticleCommentById(comment_parent_id);
            if(Utils.isNotNull(parentArticleComment)){
                com.memory.entity.ArticleComment articleComment  = new com.memory.entity.ArticleComment();
                articleComment.setId(Utils.getShortUUID());
                articleComment.setUserId(user_id);
                articleComment.setUserLogo(user_logo);
                articleComment.setUserName(user_name);
                articleComment.setArticleId(parentArticleComment.getArticleId());
                articleComment.setCommentType(1);
                articleComment.setCommentRootId(parentArticleComment.getCommentRootId());
                articleComment.setCommentParentId(comment_parent_id);
                //     if(parentArticleComment.getCommentType() == 1){
                System.out.println("parentUserName = " + "@"+parentArticleComment.getUserName());
                articleComment.setCommentParentUserName("@"+parentArticleComment.getUserName());
                articleComment.setCommentParentContent(parentArticleComment.getCommentContentReplace());
     /*       }else{
                articleComment.setCommentParentUserName("");
                articleComment.setCommentParentContent("");
            }*/
                articleComment.setCommentContent(content);
                articleComment.setCommentCreateTime(new Date());
                articleComment.setCommentTotalLike(0);
                articleComment.setCommentContentReplace(content_replace);
                com.memory.entity.ArticleComment articleComment1 =   articleCommentCmsService.addArticleComment(articleComment);
                result = ResultUtil.success(articleComment1);

            }else {
                result = ResultUtil.error(-1,"非法评论id");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }





    @RequestMapping(value = "remove")
    public  Result remove(@RequestParam("comment_id") String comment_id){
        Result result = new Result();
        try{


            List<com.memory.entity.ArticleComment> removeList = new ArrayList<com.memory.entity.ArticleComment>();

            com.memory.entity.ArticleComment articleComment = articleCommentCmsService.getArticleCommentById(comment_id);

            if(articleComment == null){
                return ResultUtil.error(-1,"非法id");
            }
            String comment_root_id = articleComment.getCommentRootId();


            if(comment_id.equals(comment_root_id)){
                articleCommentCmsService.deleteArticleCommentByCommentRootId(comment_root_id);
            }else{
                Date comment_create_time = articleComment.getCommentCreateTime();
                List<com.memory.entity.ArticleComment> list = articleCommentCmsService.queryArticleCommentList(comment_root_id,comment_create_time);
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
            articleCommentCmsService.deleteAll(removeList);
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
