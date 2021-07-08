package com.jack.jackAdvanced;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.jack.jackAdvanced.mapper")

// @SpringBootApplication 原生不使用数据库数据库方式
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class,DataSourceAutoConfiguration.class})
public class JackAdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(JackAdvancedApplication.class, args);
    }

}
