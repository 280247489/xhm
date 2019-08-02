package com.memory.cms.service.impl;

import com.memory.cms.repository.CmsTopicsRepository;
import com.memory.cms.service.CmsTopicsService;
import com.memory.common.utils.Utils;
import com.memory.domain.dao.DaoUtils;
import com.memory.entity.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * @author INS6+
 * @date 2019/7/1 19:40
 */
@Service
public class CmsTopicsServiceImpl implements CmsTopicsService {

    @Autowired
    private CmsTopicsRepository repository;

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
    public int useTopics(String id) {
        return repository.setTopicsStatusUsedById(id);
    }

    @Override
    public void deleteTopics(String id) {
         repository.deleteById(id);
    }

    @Override
    public List<Topics> queryTopicsByTopicName(String topicName) {
        return repository.queryTopicsByTopicName(topicName);
    }

    @Override
    public List<Topics> queryTopicsByQue(Integer pageIndex, Integer limit, String articleType, String sortType, Integer topicStatus,String topicName,String beginTime,String endTime) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" FROM Topics t Where 1=1 ");
        DaoUtils.Page page = daoUtils.getPage(pageIndex, limit);
        Map<String,Object> whereClause =  getQueryWhere(articleType,sortType,topicStatus,topicName,beginTime,endTime);
        stringBuffer.append(whereClause.get("where"));
        System.out.printf(" where = " + stringBuffer.toString());

        Map<String,Object> map = (  Map<String,Object>) whereClause.get("param");
        return   daoUtils.findByHQL(stringBuffer.toString(),map,page);
    }

    @Override
    public int queryTopicsCountByQue(String articleType, String sortType, Integer topicStatus,String topicName,String beginTime,String endTime) {

        Map<String,Object> whereClause =  getQueryWhere(articleType,sortType,topicStatus,topicName,beginTime,endTime);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" SELECT count(*) FROM Topics t Where 1=1 ");

        stringBuffer.append(whereClause.get("where"));

        Map<String,Object> map = (  Map<String,Object>) whereClause.get("param");


        return daoUtils.getTotalByHQL(stringBuffer.toString(),map);
    }

    public Map<String,Object> getQueryWhere( String articleType, String sortType, Integer topicStatus,String topicName,String beginTime,String endTime){
        Map<String,Object> returnMap = new HashMap<String, Object>();
        StringBuffer stringBuffer = new StringBuffer();
        Map<String,Object> paramMap = new HashMap<String, Object>();
        if(Utils.isNotNull(topicName)){
            stringBuffer.append(" AND t.topicName like :topicName");
            paramMap.put("topicName","%"+ topicName+"%");
        }

        if(Utils.isNotNull(articleType) ){
            stringBuffer.append(" AND t.articleTypeId = :articleType");
            paramMap.put("articleType",articleType);
        }

        if(Utils.isNotNull(topicStatus)){
            stringBuffer.append(" AND t.topicStatus = :topicStatus");
            paramMap.put("topicStatus",topicStatus);
        }


        if(Utils.isNotNull(beginTime) ){
            try {
                Date sTime = Utils.sf_yMd.parse(beginTime);
                paramMap.put("beginTime",sTime);
                stringBuffer.append(" and t.topicCreateTime >= :beginTime");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(!Utils.isNotNull(endTime) ){
            try {

                Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
                c.setTime(Utils.sf_yMd.parse(endTime));
                c.add(Calendar.DATE, 1);
                paramMap.put("endTime", c.getTime());
                stringBuffer.append(" and t.topicCreateTime <= :endTime");
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }



        //排序方式
        //1. sumDesc   sumAsc 阅读数 2. sortDesc sortAsc  排序 3.timeDesc timeAsc 创建时间
        if(Utils.isNotNull(sortType)){
            if("sumDesc".equals(sortType)){
                stringBuffer.append(" ORDER BY t.topicSum DESC ");
            }
            if("sumAsc".equals(sortType)){
                stringBuffer.append(" ORDER BY t.topicSum ASC ");
            }

            if("sortDesc".equals(sortType)){
                stringBuffer.append(" ORDER BY t.topicSort DESC ");
            }
            if("sortAsc".equals(sortType)){
                stringBuffer.append(" ORDER BY t.topicSort ASC ");
            }

            if("timeDesc".equals(sortType)){
                stringBuffer.append(" ORDER BY t.topicCreateTime DESC ");
            }
            if("timeAsc".equals(sortType)){
                stringBuffer.append(" ORDER BY t.topicCreateTime ASC ");
            }

            //默认根据时间倒叙
        }else {
            stringBuffer.append(" ORDER BY t.topicCreateTime DESC ");
        }



        returnMap.put("where",stringBuffer.toString());
        returnMap.put("param",paramMap);

        return returnMap;
    }


}
