package com.fourzhang.youddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class Test {
    @RequestMapping("/test")
    public String test(){
        return "123";
    }
    @RequestMapping("/test1")
    public String test1(){
        return "test1";
    }
}
