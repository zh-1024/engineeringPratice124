package com.fourzhang.youddit.service.impl;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.param.ContentParam;
import com.fourzhang.youddit.mapper.*;
import com.fourzhang.youddit.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishServiceImpl implements PublishService {
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private ContentLabelMapper contentLabelMapper;
    @Autowired
    private ContentImageMapper contentImageMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private LabelMapper labelMapper;
    @Override
    public Result publish(ContentParam cm) {

        return null;
    }
}
