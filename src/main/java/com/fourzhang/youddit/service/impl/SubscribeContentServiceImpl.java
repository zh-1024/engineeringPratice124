package com.fourzhang.youddit.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fourzhang.youddit.entity.Content;
import com.fourzhang.youddit.mapper.ContentMapper;
import com.fourzhang.youddit.response.ContentResponse;
import com.fourzhang.youddit.service.SubscribeContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscribeContentServiceImpl implements SubscribeContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public ContentResponse loadSubscribeContent(Long id, Integer from, Integer num) {
        Page<Content> page = new Page<>(from, num);
        Page<Content> res = contentMapper.findSubscribeContents(id, page);

        ContentResponse response = new ContentResponse();
        response.setContents(res.getRecords());
        response.setPages(res.getPages());
        response.setTotal(res.getTotal());
        response.setHasNext(res.hasNext());
        response.setHasPrevious(res.hasPrevious());

        return response;
    }
    
}
