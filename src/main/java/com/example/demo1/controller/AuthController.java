package com.example.demo1.controller;

import com.example.demo1.common.Result;
import com.example.demo1.dto.LoginDTO;
import com.example.demo1.service.AuthService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.example.demo1.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 表示这是一个接口类，返回JSON数据
public class AuthController {

    @Autowired
    // 自动注入 AuthService（不用你自己 new）
    private AuthService authService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody LoginDTO dto) {

        // 判断用户名和密码是否正确（调用 AuthService 的 login 方法）
        boolean result = authService.login(
                dto.getUsername(), // 从 dto 中获取用户名 (authService.login()的参数)
                dto.getPassword()  // 从 dto 中获取密码 (authService.login()的参数)
        );

        if (result) {
            // ⭐ 生成唯一 token
            String token = UUID.randomUUID().toString();

            // ⭐ 把 token 存到 Redis，设置过期时间（比如 30 分钟）
            redisUtil.set(token, dto.getUsername(), 30);

            // ⭐ 用 Map 存返回数据（非常常见写法）
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("username", dto.getUsername());

            return Result.success(data);
        } else {
            return Result.error("用户名或密码错误");
        }
    }
}