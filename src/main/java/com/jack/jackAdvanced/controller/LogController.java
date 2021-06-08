package com.jack.jackAdvanced.controller;

import com.jack.jackAdvanced.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  引入LogBack日志框架
 */

@RequestMapping("/log")
@RestController
@Slf4j
public class LogController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello(){
        log.info("info log......");
        log.error("error log......");

        try {
            log.info("info get user log......");
            userService.getById("11").getAddress();
        }catch (Exception e){
            log.error("获取用户信息失败",e);
        }

        try {
            log.info("info calc math log......");
            int a = 10/0;
        }catch (Exception e){
            log.error("计算失败",e);
        }

        return "hello logback";
    }

}
