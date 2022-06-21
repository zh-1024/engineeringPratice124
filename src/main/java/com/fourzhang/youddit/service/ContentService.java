package com.fourzhang.youddit.service;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.entity.Content;
import com.fourzhang.youddit.response.ContentResponse;

import java.security.Principal;

/**
 * @author zh
 * TODO: 此模块包含查询个人帖子、收藏帖子和他人帖子相关接口
 */
public interface ContentService {
    public Result<ContentResponse> getSelfPublishContents(Long currentPage, Long pageSize, Principal principal);
    public Result<ContentResponse> getSelfCollectContents(Long currentPage,Long pageSize,Principal principal);
    public Result getLables();
    public Result<Content> getContent(Long id);
}
