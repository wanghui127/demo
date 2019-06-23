package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class WebAppConfigurer implements WebMvcConfigurer  {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截的管理器
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/","/login", "/getCode","/checkCode","/static/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        /*registry.addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/static/layui/**").addResourceLocations("classpath:/static/layui/");
        registry.addResourceHandler("/static/jq/**").addResourceLocations("classpath:/static/jq/");
        registry.addResourceHandler("/static/images/**").addResourceLocations("classpath:/static/images/");
        WebMvcConfigurer.super.addResourceHandlers(registry);*/
    }
}
