package com.memory.app.service.impl;

import com.memory.app.repository.TopicsRepository;
import com.memory.app.service.TopicsService;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author INS6+
 * @date 2019/7/10 14:08
 */
@Service
public class TopicsServiceImpl implements TopicsService {

    @Autowired
    private TopicsRepository repository;

    @Autowired
    private DaoUtils daoUtils;

    @Override
    public Topics add(Topics topics) {
        return repository.save(topics);
    }

    @Override
    public Topics update(Topics topics) {
        return repository.save(topics);
    }

    @Override
    public Topics getTopicsById(String id) {
        if(repository.findById(id).hashCode() != 0){
            return repository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public List<Topics> queryTopicsByTopicName(String topicName) {
        StringBuffer stringBuffer = new StringBuffer();
        Map<String,Object> mapper = new HashMap<String, Object>();
        stringBuffer.append(" FROM Topics t where 1=1 ");
        stringBuffer.append(" AND t.topicName like :topicName");
        stringBuffer.append(" AND t.topicStatus = 1 ");
        stringBuffer.append(" ORDER BY t.topicSort,t.topicSum,t.topicCreateTime");
        mapper.put("topicName","%"+topicName+"%");
        return daoUtils.findByHQL(stringBuffer.toString(),mapper,null);
    }



    @Override
    public List<Topics> queryHotTopics(int pageIndex,int pageLimit) {
        StringBuffer stringBuffer = new StringBuffer();
        Map<String,Object> mapper = new HashMap<String, Object>();
        stringBuffer.append(" FROM Topics t where 1=1 ");
        stringBuffer.append(" AND t.topicStatus = 1 ");
        stringBuffer.append(" ORDER BY t.topicSort,t.topicSum,t.topicCreateTime");

        DaoUtils.Page page = daoUtils.getPage(pageIndex, pageLimit);

        return daoUtils.findByHQL(stringBuffer.toString(),mapper,page);
    }







}
