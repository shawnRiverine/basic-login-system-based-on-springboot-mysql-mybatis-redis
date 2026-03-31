package com.example.demo1.service;

import com.example.demo1.entity.Auth;
import com.example.demo1.mapper.AuthMapper;
import com.example.demo1.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// 标记这是一个“业务类”，Spring会自动管理它（可以被注入）
public class AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private RedisUtil redisUtil;

    // ⭐ 登录方法，返回 true/false 表示登录成功/失败
    public boolean login(String username, String password) {

        Auth auth = authMapper.findByUsername(username);

        if (auth == null) {
            return false;
        }

        // 简单比对密码（后面可以升级加密）
        if (password.equals(auth.getPassword())) {
            return true;
        }

        return false;
    }

    // ⭐ 查询 age（带缓存）
    public Integer getAge(String username) {

        String key = "user:" + username;

        // 1️⃣ 查 Redis
        String value = redisUtil.get(key);

        if (value != null) {
            // Redis 命中，直接返回（记得转 Integer）
            return Integer.parseInt(value);
        }

        // 2️⃣ 查 MySQL
        Auth auth = authMapper.findByUsername(username);

        if (auth == null) {
            return null;
        }

        // 3️⃣ 存 Redis（注意转 String）
        redisUtil.set(key, String.valueOf(auth.getAge()), 30);

        // 4️⃣ 返回结果
        return auth.getAge();
    }
}