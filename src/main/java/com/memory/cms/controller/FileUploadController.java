package com.memory.cms.controller;

import com.memory.common.controller.BaseController;
import com.memory.common.utils.Result;
import com.memory.common.utils.ResultUtil;
import com.memory.common.utils.Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author INS6+
 * @date 2019/9/11 17:54
 */
@RestController
@RequestMapping("fileUpload")
public class FileUploadController extends BaseController {

    @RequestMapping("uploadFile")
    public Result uploadFile(HttpServletRequest request){
        Result result = new Result();
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);

        MultipartFile file = multipartRequest.getFile("fileList");

        String path = this.upload2PNG(Utils.getShortUUID(), "xhm_file/article/"+Utils.getShortUUID(), file);

        Map<String,Object> returnMap = new HashMap<String, Object>();
        returnMap.put("path", path);

        result = ResultUtil.success(returnMap);

        return result;
    }



}
