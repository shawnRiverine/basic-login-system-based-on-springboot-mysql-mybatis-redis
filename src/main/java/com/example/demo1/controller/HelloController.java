package com.example.demo1.controller;

import com.example.demo1.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/hello2")
    public Object helloObject() {
        return new User("张三", 20);
    }
}
