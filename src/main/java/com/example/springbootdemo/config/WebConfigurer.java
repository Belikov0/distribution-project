package com.example.springbootdemo.config;

import com.example.springbootdemo.interceptor.WorkerJwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor getWorkerJwtInterceptor(){
        return new WorkerJwtInterceptor();
    }

    /**
     * Resolve the problem of CORS
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(10000);
        WebMvcConfigurer.super.addCorsMappings(registry);
    }

    /**
     * Register interceptors
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getWorkerJwtInterceptor())
                .addPathPatterns("/worker/**")
                .excludePathPatterns("/worker/login");
        WebMvcConfigurer.super.addInterceptors(registry);
    }


}
