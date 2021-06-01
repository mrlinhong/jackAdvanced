package com.jack.jackAdvanced;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.jack.jackAdvanced.mapper")
@SpringBootApplication
public class JackAdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(JackAdvancedApplication.class, args);
    }

}
