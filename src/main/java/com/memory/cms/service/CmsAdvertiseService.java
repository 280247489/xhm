package com.memory.cms.service;

import com.memory.entity.Advertise;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @ClassName CmsAdvertiseService
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/6/29 18:13
 */
public interface CmsAdvertiseService {
    @Transactional
    Advertise add(Advertise advertise);

    Map<String,Object> list(Integer start, Integer limit, String name);

    @Transactional
    Advertise online(String aid);
}
