package com.cignacmb.smart.base.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author: caokang
 * @Date: Created in 11:13 2020/4/30
 * @annotation:过滤器接口
 * @version:1.0
 * @TableName:
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        InterceptorRegistration ir = registry.addInterceptor(new AuthorizationInterceptor());
        ir.addPathPatterns("/login/selectMenuList");
    }
}
