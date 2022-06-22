package com.power.platform.handler;

import com.alibaba.fastjson.JSON;
import com.power.platform.utils.JWTTokenUtils;
import com.power.platform.vo.Result;
import com.power.platform.vo.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 这里校验有没有token头同时判断其是否合法
        String token = request.getHeader(JWTTokenUtils.TOKEN_HEADER);
        if(token == null || !redisTemplate.hasKey(token)){
            response.setStatus(402);
            response.getWriter().println(JSON.toJSONString(Result.fail(ResultEnum.ILLEGAL_TOKEN)));
            return false;
        }
//        if(!redisTemplate.hasKey(token)){
//            response.setStatus(402);
//            response.getWriter().write("Token illegal!");
//            return false;
//        }
        try {
            JWTTokenUtils.getIdByToken(token);
        }catch (Exception e){
            response.setStatus(402);
            response.getWriter().println(JSON.toJSONString(Result.fail(ResultEnum.ILLEGAL_TOKEN)));
            return false;
        }
        return true;
    }
}
