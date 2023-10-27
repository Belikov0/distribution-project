//package com.example.springbootdemo.config;
//
//import com.example.springbootdemo.interceptor.LoginInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration //添加Configuration注解使之生效
//public class WebConfigurer implements WebMvcConfigurer {
//
//    @Override
//    // 注册拦截器//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/");
//    }
//}
