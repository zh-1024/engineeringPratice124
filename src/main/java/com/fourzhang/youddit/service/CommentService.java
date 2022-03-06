package com.fourzhang.youddit.service;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.request.CommentRequest;

public interface CommentService {
    public Result commentComment(CommentRequest commentRequest,String usrname) throws Exception;
}
