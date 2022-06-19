package com.power.platform.handler;

import com.power.platform.utils.JWTTokenUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 这里校验有没有token头同时判断其是否合法
        String token = request.getHeader(JWTTokenUtils.TOKEN_HEADER);
        if(token == null){
            response.setStatus(402);
            response.getWriter().write("Not contain token!");
        }
        return false;
    }
}
