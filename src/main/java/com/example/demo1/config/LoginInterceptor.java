package com.example.demo1.config;

import com.example.demo1.utils.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        String token = request.getHeader("token");

        // 没 token
        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            return false;
        }

        // ⭐ 查 Redis
        String username = redisUtil.get(token);

        // 传入的 token 对应的 username 不存在（说明无效）
        if (username == null) {
            response.setStatus(401);
            return false;
        }

        // 有效 token → 放行
        return true;
    }
}