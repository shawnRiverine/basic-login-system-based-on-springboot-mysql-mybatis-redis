package com.example.demo1.mapper;

import com.example.demo1.entity.Auth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
// 告诉 Spring：这是一个 Mapper（数据库操作类）
public interface AuthMapper {

    @Select("SELECT * FROM auth WHERE username = #{username}")
    // #{username} = 方法参数（自动替换）

    Auth findByUsername(String username);
    // 返回值是 Auth → 表示查询结果会变成一个对象
}