package com.memory.app.redis.dic;

/**
 * @author INS6+
 * @date 2019/7/23 20:00
 */

public class RedisDic {

    //用户关注信息
    //key: => user:collection:用户id
    //val:hashMap => key:被关注人id value:是否关注 0/1
    public static final String USERCOLLECTION ="user:collection:";

    //用户收藏信息
    //key: => user:collection:用户id
    //val:hashMap => key:被关注人id value:是否关注 0/1
    public static final String USERFOLLOW ="user:follow:";




}
