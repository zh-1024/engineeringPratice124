package com.fourzhang.youddit.controller;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.ResultTool;
import com.fourzhang.youddit.entity.CommentComment;
import com.fourzhang.youddit.entity.ContentComment;
import com.fourzhang.youddit.request.CommentCommentRequest;
import com.fourzhang.youddit.request.CommentRequest;
import com.fourzhang.youddit.request.ContentCommentRequest;
import com.fourzhang.youddit.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/social")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/commentComment")
    public Result commentComment(@RequestBody CommentRequest commentrequest, Principal principal) throws Exception {
        String name = principal.getName();
        return commentService.commentComment(commentrequest, name);
    }

    @PostMapping("/contentComment")
    public Result contentComment(@RequestBody CommentRequest commentrequest, Principal principal) {
        String name = principal.getName();
        return commentService.contentComment(commentrequest, name);
    }

    @PostMapping("/deleteContentComment/{commentId}")
    public Result deleteContentComment(@PathVariable Long commentId) {
        return commentService.deleteContentComment(commentId);
    }

    @PostMapping("/deleteCommentComment/{commentId}")
    public Result deleteCommentComment(@PathVariable Long commentId) {
        return commentService.deleteCommentComment(commentId);
    }

    @PostMapping("load")
    public Result<List<ContentComment>> loadComment(@RequestBody ContentCommentRequest request) {
        List<ContentComment> comments = commentService.loadContentComment(request.getContentId(), request.getFrom(),
                request.getNum());

        return ResultTool.success(comments);
    }

    @PostMapping("loadsub")
    public Result<List<CommentComment>> loadSubComment(@RequestBody CommentCommentRequest request) {
        List<CommentComment> comments = commentService.loadCommentComment(request.getContentCommentId(),
                request.getFrom(), request.getNum());

        return ResultTool.success(comments);
    }
}
