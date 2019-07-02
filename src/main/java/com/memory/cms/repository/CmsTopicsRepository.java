package com.memory.cms.repository;

import com.memory.entity.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author INS6+
 * @date 2019/7/1 19:35
 */

public interface CmsTopicsRepository extends JpaRepository<Topics,String> {

    @Modifying
    @Query("update Topics t set t.topicStatus =1 where t.id=?1")
    int setTopicsStatusUsedById(String id);

    List<Topics> queryTopicsByTopicName(String topicName);

}
