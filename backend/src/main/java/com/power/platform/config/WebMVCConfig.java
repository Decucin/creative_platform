package com.power.platform.config;

import com.power.platform.handler.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .excludePathPatterns("/login")
                .excludePathPatterns("/registry")
                .excludePathPatterns("/articles/lists/all")
                .excludePathPatterns("/articles/all")
                .excludePathPatterns("/articles/inspirations/all")
                .excludePathPatterns("/doc.html") //不需要拦截的地
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/v3/**")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("/swagger-ui/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 跨域配置，将前端项目部署在3000端口
        registry.addMapping("/**").allowedOrigins("http://localhost:3000");
    }
}
