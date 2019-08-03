package com.memory.app.redis.dic;

/**
 * @author INS6+
 * @date 2019/7/23 20:00
 */

public class RedisDic {




    //用户点赞信息
    //key: => user:like:articleId
    //val:hashMap => key:article value:是否点赞 0/1
    public static final String userLike ="user:like:";


    //用户文章点赞信息
    //key: => article:like:total: articleId
    //val:count 文章点赞次数
    public static final String articleLikes = "article:like:total:";


    //文章收藏数量
    //key: => user:follow:articleId
    //val:count 文章收藏数量
    public static final String ARTICLEFOLLOW ="article:follow:";

    //用户关注信息
    //key: => user:collection:用户id
    //val:hashMap => key:被收藏的文章id value:是否关注 0/1
    public static final String USERCOLLECTION ="user:collection:";



    //用户文章收藏信息
    //key: =>user:follow:userId
    //val:hashMap => key:articleId value:是否收藏 0/1
    public static final String USERFOLLOW ="user:follow:";

    //文章转发信息
    public static final String articleForward="article:forward:";


    //文章评论的点赞数量
    //key article:comment:like:commentId
    //val count 评论点赞数
    public static final String articleCommentLike ="article:comment:like:";

    //用户文章评论的点赞 是否
    //key article:comment:like:detail: userId
    //val:hashMap => key:commentId value:是否点赞 0/1
    public static final String articleCommentLikeDetail ="user:comment:like:detail:";



}
