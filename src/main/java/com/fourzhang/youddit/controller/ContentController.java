package com.fourzhang.youddit.controller;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.param.ContentParam;
import com.fourzhang.youddit.service.DeleteContentService;
import com.fourzhang.youddit.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/content")
public class ContentController {
    @Autowired
    private PublishService publishService;
    @Autowired
    private DeleteContentService deleteContentService;
    @PostMapping("publish")
    public Result publish(@RequestBody ContentParam contentParam){
        return publishService.publish(contentParam);
    }
    @PostMapping("delete/{content_id}")
    public Result delete(@PathVariable long content_id){
        return deleteContentService.deletePub(content_id);
    }
}
