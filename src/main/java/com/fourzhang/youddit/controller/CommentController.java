package com.fourzhang.youddit.controller;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.request.CommentRequest;
import com.fourzhang.youddit.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequestMapping("api/social")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("commentComment")
    public Result commentComment(@RequestBody CommentRequest commentrequest, Principal principal) throws Exception {
        String name=principal.getName();
        return  commentService.commentComment(commentrequest,name);
    }
}
