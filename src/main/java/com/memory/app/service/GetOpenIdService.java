package com.memory.app.service;

import java.util.Map;

/**
 * @ClassName GetOpenIdService
 * @Descriotion TODO
 * @Author Ganxiqing
 * @Date 2019/7/30 13:27
 */
public interface GetOpenIdService {
      Map<String,Object> getOpenId(String code);
}
