package com.fourzhang.youddit.service;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.response.ContentResponse;

public interface RecommendContentService {
    public Result<ContentResponse> getContents(Long labelId,Long currentPage,Long pageSize);
}
