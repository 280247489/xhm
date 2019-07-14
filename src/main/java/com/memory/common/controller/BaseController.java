package com.memory.common.controller;

import com.memory.common.utils.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Auther: cui.Memory
 * @Date: 2019/5/9 0009 10:47
 * @Description:
 */
public class BaseController {
    private final static Logger base_logger = LoggerFactory.getLogger(BaseController.class);
    protected Message msg;
    @Value(value = "${filePath}")
    private String filePath;
    @Value(value = "${fileUrl}")
    private String fileUrl;

    public String getFileUrl() {
        return fileUrl;
    }

    public String upload(String fileName, String dirPath, MultipartFile file){
        String db_path = "";
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
            File dir = new File(filePath + dirPath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            File destFile = new File(dir.getPath() + File.separator + fileName + suffix);
            file.transferTo(destFile);
            db_path = dirPath + File.separator + fileName + suffix;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return db_path;
    }
    public String upload2PNG(String fileName, String dirPath, MultipartFile file){
        String db_path = "";
        try {
            File dir = new File(filePath + dirPath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            File destFile = new File(dir.getPath() + File.separator + fileName + ".png");
            file.transferTo(destFile);
            db_path = dirPath + File.separator + fileName + ".png";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return db_path;
    }
    public String upload2MP3(String fileName, String dirPath, MultipartFile file){
        String db_path = "";
        try {
            File dir = new File(filePath + dirPath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            File destFile = new File(dir.getPath() + File.separator + fileName + ".mp3");
            file.transferTo(destFile);
            db_path = dirPath + File.separator + fileName + ".mp3";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return db_path;
    }

    public String upload2MP4(String fileName, String dirPath, MultipartFile file){
        String db_path = "";
        try {
            File dir = new File(filePath + dirPath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            File destFile = new File(dir.getPath() + File.separator + fileName + ".mp4");
            file.transferTo(destFile);
            db_path = dirPath + File.separator + fileName + ".mp4";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return db_path;
    }
}
