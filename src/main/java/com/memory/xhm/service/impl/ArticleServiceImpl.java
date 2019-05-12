package com.memory.xhm.service.impl;

import com.memory.domain.dao.DaoUtils;
import com.memory.xhm.entity.Article;
import com.memory.xhm.repository.ArticleRepository;
import com.memory.xhm.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/8 0008 13:46
 * @Description:
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private DaoUtils daoUtils;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Map<String, Object> sel(Integer start, Integer limit) {
        Map<String, Object> reutnrMap = new HashMap<>();
        StringBuffer sb =new StringBuffer(" from Article where articleCheckYn = 1 and articleDelYn = 0  order by articleCheckTime desc");
        StringBuffer sb_count =new StringBuffer("select count(*) from Article where articleCheckYn = 1 and articleDelYn = 0 ");
        DaoUtils.Page page = daoUtils.getPage(start, limit);
        reutnrMap.put("list", daoUtils.findByHQL(sb.toString(), null, page));
        reutnrMap.put("count", daoUtils.getTotalByHQL(sb_count.toString(), null));
        reutnrMap.put("start", start);
        reutnrMap.put("limit", limit);
        return reutnrMap;
    }

    @Override
    public Map<String, Object> selByUserId(String userId, Integer start, Integer limit) {
        Map<String, Object> reutnrMap = new HashMap<>();
        StringBuffer sb =new StringBuffer(" from Article where articleCreateUserId=:userId And articleDelYn = 0  order by articleCheckTime desc");
        StringBuffer sb_count =new StringBuffer("select count(*) from Article where articleCreateUserId=:userId And articleDelYn = 0 ");
        DaoUtils.Page page = daoUtils.getPage(start, limit);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        reutnrMap.put("list", daoUtils.findByHQL(sb.toString(), map, page));
        reutnrMap.put("count", daoUtils.getTotalByHQL(sb_count.toString(), map));
        reutnrMap.put("start", start);
        reutnrMap.put("limit", limit);

        return reutnrMap;
    }

    @Transient
    @Override
    public Article add(Article article) {
        articleRepository.save(article);
        return article;
    }
}
