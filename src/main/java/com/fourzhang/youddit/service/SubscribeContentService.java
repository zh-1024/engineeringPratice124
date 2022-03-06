package com.fourzhang.youddit.service;

import com.fourzhang.youddit.response.ContentResponse;

public interface SubscribeContentService {
    ContentResponse loadSubscribeContent(Long id, Integer from, Integer num);
}
