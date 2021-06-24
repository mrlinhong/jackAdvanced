package com.jack.jackAdvanced.guava.ratelimite;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * test接口，1秒钟生成100个令牌，也就是1秒中允许100人访问
         */
        registry.addInterceptor(new RateLimiterInterceptor(
                RateLimiter.create(100, 1, TimeUnit.SECONDS)))
                .addPathPatterns("/rate/limite/test");
    }
}
