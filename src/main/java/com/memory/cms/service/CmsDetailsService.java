package com.memory.cms.service;

import com.memory.entity.Details;
import com.memory.entity.User;

/**
 * @ClassName CmsDetailsService
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/23 19:38
 */
public interface CmsDetailsService {
    void addIntegral(User user, Details details);

    void addDetails(Details details);
}
