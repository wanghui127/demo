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
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/test/**","/","/login", "/getCode","/checkCode","/static/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    /** 
    * @Description:  放开静态资源不拦截(上下两处都需要写，也不知道为啥)
    * @Param:
    * @return:  
    * @Author: Hui.Wang 
    * @Date: 2019/6/23 0023 
    * @Time: 21:00 
    */ 
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
