package com.fourzhang.youddit.service;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.response.ContentResponse;
// import com.fourzhang.youddit.response.LabelResponse;

import java.util.List;

public interface RecommendContentService {
    public Result<ContentResponse> getContentsByLabel(Long labelId,Long currentPage,Long pageSize);
    public Result<ContentResponse> getContentsAll(Long currentPage,Long pageSize);
    // public Result<LabelResponse>  getLabels(Long currentPage,Long pageSize);
    public Result<List<String>>  getImagesByContentId(Long contentId);
}
