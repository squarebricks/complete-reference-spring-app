package com.orgofarmsgroup.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RestrictionApiInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestrictionApiInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("RestrictionApiInterceptor.preHandle()");
        return true;
    }
}
