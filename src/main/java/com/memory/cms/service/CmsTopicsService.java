package com.memory.cms.service;

import com.memory.entity.Topics;

import java.util.List;

/**
 * @author INS6+
 * @date 2019/7/1 19:40
 */

public interface CmsTopicsService {

    Topics add(Topics topics);

    Topics update(Topics topics);

    Topics getTopicsById(String id);

    List<Topics> queryTopicsByQue(Integer pageIndex,Integer limit,String articleType,String sortType,Integer topicStatus,String topicName);

    int queryTopicsCountByQue(String articleType,String sortType,Integer topicStatus,String topicName);

    //启用话题
    int useTopics(String id);

    //移除话题(只针对未被启用的话题)
    void deleteTopics(String id);

    List<Topics> queryTopicsByTopicName(String topicName);

}
