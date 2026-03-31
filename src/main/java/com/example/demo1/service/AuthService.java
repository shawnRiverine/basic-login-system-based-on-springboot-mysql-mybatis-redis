package com.example.demo1.service;

import com.example.demo1.entity.Auth;
import com.example.demo1.mapper.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// 标记这是一个“业务类”，Spring会自动管理它（可以被注入）
public class AuthService {

    @Autowired
    private AuthMapper authMapper;

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
}