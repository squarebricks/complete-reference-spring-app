package com.orgofarmsgroup.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebRequestConfig implements WebMvcConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebRequestConfig.class);
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LOGGER.info("WebRequestConfig.addInterceptors()");
        registry.addInterceptor(new RestrictionApiInterceptor())
                .addPathPatterns("/","/**");
    }
}
