package com.fourzhang.youddit.controller;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.param.ContentParam;
import com.fourzhang.youddit.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/content")
public class ContentController {
    @Autowired
    private PublishService publishService;
    @PostMapping("publish")
    public Result publish(@RequestBody ContentParam contentParam){
        return publishService.publish(contentParam);
    }
}
