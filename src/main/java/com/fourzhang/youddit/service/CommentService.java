package com.fourzhang.youddit.service;

import java.util.ArrayList;
import java.util.List;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.entity.CommentComment;
import com.fourzhang.youddit.entity.ContentComment;
import com.fourzhang.youddit.request.CommentRequest;

public interface CommentService {
    public Result commentComment(CommentRequest commentRequest,String usrname) throws Exception;

    public Result contentComment(CommentRequest commentRequest,String usrname);

    public Result deleteContentComment(Long commentId);

    public Result deleteCommentComment(Long commentId);

    public List<ContentComment> loadContentComment(Long contentId, Integer from, Integer num);

    public List<CommentComment> loadCommentComment(Long commentId, Integer from, Integer num);
}
