package com.example.demo1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")   // 拦截所有请求
                .excludePathPatterns("/login") // 放行登录接口
                .excludePathPatterns("/hello")
                .excludePathPatterns("/hello2")
                ;
                //.excludePathPatterns("/search"); // 放行查询接口
    }
}