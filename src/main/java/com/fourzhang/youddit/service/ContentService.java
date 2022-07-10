package com.fourzhang.youddit.service;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.response.ContentResponse;

import java.security.Principal;

/**
 * @author zh
 */
public interface ContentService {
    public Result<ContentResponse> getSelfPublishContents(Long currentPage, Long pageSize, Principal principal);
    public Result<ContentResponse> getSelfCollectContents(Long currentPage,Long pageSize,Principal principal);
    public Result getLabels();
    public Result getContent(Long id);
}
