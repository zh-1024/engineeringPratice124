package com.fourzhang.youddit.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.entity.Content;
import com.fourzhang.youddit.mapper.ContentLabelMapper;
import com.fourzhang.youddit.response.ContentResponse;
import com.fourzhang.youddit.service.RecommendContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO: 内容推荐相关逻辑业务处理
 * @author zh
 * @version 1.0
 * @date 2022/3/6 000615:25
 */
@Service
public class RecommendContentServiceImpl implements RecommendContentService {
    @Autowired
    private ContentLabelMapper contentLabelMapper;
    @Override
    public Result<ContentResponse> getContents(Long labelId, Long currentPage, Long pageSize) {

        Page<Content> page = new Page<>(currentPage, pageSize);
        Page<Content> res = contentLabelMapper.selectContentsByLabel(page, labelId);

        ContentResponse contentResponse = new ContentResponse();
        contentResponse.setContents(res.getRecords());
        contentResponse.setPages(res.getPages());
        contentResponse.setTotal(res.getTotal());
        contentResponse.setHasNext(res.hasNext());
        contentResponse.setHasPrevious(res.hasPrevious());

        Result<ContentResponse> contentResponseResult = new Result<>(contentResponse);
        return contentResponseResult;
    }
}
