package com.memory.cms.controller;

import com.memory.common.utils.Result;
import com.memory.common.utils.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author INS6+
 * @date 2019/6/28 17:52
 */

public class CmsArticleCommentController {

    @RequestMapping("route")
    public Result method(){
        Result result = new Result();
        try {

            result = ResultUtil.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
