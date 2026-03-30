package com.example.demo1.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    // 存数据（带过期时间）
    public void set(String key, String value, long timeoutMinutes) {
        redisTemplate.opsForValue().set(key, value, timeoutMinutes, TimeUnit.MINUTES);
    }

    // 取数据
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 删除
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}