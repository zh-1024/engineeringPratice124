package com.fourzhang.youddit.controller;

import java.security.Principal;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.ResultCode;
import com.fourzhang.youddit.data.ResultTool;
import com.fourzhang.youddit.entity.User;
import com.fourzhang.youddit.request.PageRange;
import com.fourzhang.youddit.response.ContentResponse;
import com.fourzhang.youddit.service.SubscribeContentService;
import com.fourzhang.youddit.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscribeController {
    @Autowired
    private UserService userService;

    @Autowired
    private SubscribeContentService subscribeContentService;

    @GetMapping("/subscribe/fetchcontent")
    public Result<ContentResponse> fetchContent(@RequestBody PageRange range , Principal principal) {
        User user = userService.findUserByName(principal.getName());
        if (user == null) { return ResultTool.dataFail(ResultCode.COMMON_FAIL); }

        ContentResponse response =  subscribeContentService.loadSubscribeContent(user.getId(), 
                                                                range.getFrom(), 
                                                                range.getNum());
        
        return ResultTool.success(response);                                                        
    }
}
