package com.memory.app.service;

import com.memory.entity.Topics;

import java.util.List;

/**
 * @author INS6+
 * @date 2019/7/10 14:08
 */

public interface TopicsService {

    Topics add(Topics topics);

    Topics update(Topics topics);


    Topics getTopicsById(String id);

    //根据话题模糊查询列表
    List<Topics> queryTopicsByTopicName(String topicName);


    List<Topics> queryHotTopics(int pageIndex, int pageLimit);
}
