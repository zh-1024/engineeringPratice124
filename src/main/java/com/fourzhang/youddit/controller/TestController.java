package com.fourzhang.youddit.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class TestController {
    @RequestMapping("/test")
    public String test(HttpServletResponse response) {
        return "This is test";
    }
    @RequestMapping("/test1")
    public String test1() {
        return "This is test1";
    }
}
