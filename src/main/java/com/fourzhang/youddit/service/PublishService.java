package com.fourzhang.youddit.service;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.request.ContentParam;

import java.security.Principal;

public interface PublishService {
    Result publish(ContentParam contentParam, Principal principal);
    // Result upDateContent(ContentParam contentParam, Principal principal,Long
    // content_id);
}
