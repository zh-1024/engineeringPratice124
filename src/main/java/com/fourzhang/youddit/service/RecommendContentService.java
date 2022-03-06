package com.fourzhang.youddit.service;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.response.ContentResponse;
import com.fourzhang.youddit.response.LabelResponse;

public interface RecommendContentService {
    public Result<ContentResponse> getContents(Long labelId,Long currentPage,Long pageSize);
    public Result<LabelResponse>  getLabels(Long currentPage,Long pageSize);
}
