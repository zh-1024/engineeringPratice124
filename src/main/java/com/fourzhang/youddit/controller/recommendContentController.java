package com.fourzhang.youddit.controller;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.response.ContentResponse;
import com.fourzhang.youddit.service.impl.RecommendContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO: 推荐模块相关接口
 * @author zh
 * @version 1.0
 * @date 2022/3/6 000616:02
 */
@RestController
@RequestMapping("api/recommend")
public class recommendContentController {
    @Autowired
    RecommendContentServiceImpl recommendContentService;

    @GetMapping("/getContentsByLabel")
    public Result<ContentResponse> getContentsByLabel(@RequestParam(required = true,value = "labelId")long labelId,
                                               @RequestParam(required = true,value = "currentPage")long currentPage,
                                               @RequestParam(required = true,value = "pageSize")long pageSize){
        return recommendContentService.getContentsByLabel(labelId, currentPage, pageSize);
    }
    @GetMapping("/getContents")
    public Result<ContentResponse> getContents(@RequestParam(required = true,value = "currentPage")long currentPage,
                                               @RequestParam(required = true,value = "pageSize")long pageSize){
        return recommendContentService.getContentsAll(currentPage,pageSize);
    }
    @GetMapping("/getImages")
    public Result<List<String>> getLabels(@RequestParam(required=true,value="contentId") Long contentId){
        return  recommendContentService.getImagesByContentId(contentId);
    }

}
