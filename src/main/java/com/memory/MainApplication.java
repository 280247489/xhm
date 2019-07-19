package com.memory;

import com.memory.common.utils.BadWordUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MainApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MainApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }


    @PostConstruct
    public void init(){

        load_illegalWord_2_memory();

    }

    /**
     * 违禁词项目启动初始化，加载到内存中
     */
    public void load_illegalWord_2_memory(){
        BadWordUtil.initWords();
    }



}
