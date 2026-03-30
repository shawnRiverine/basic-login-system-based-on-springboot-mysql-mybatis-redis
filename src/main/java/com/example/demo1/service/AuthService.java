package com.example.demo1.service;

import org.springframework.stereotype.Service;

@Service
// 标记这是一个“业务类”，Spring会自动管理它（可以被注入）
public class AuthService {

    public boolean login(String username, String password) {
        // 登录逻辑（现在是写死的，后面会改成查数据库）
        return "yanxu".equals(username) && "123456".equals(password);
    }
}