package com.fourzhang.youddit.service.impl;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.param.ContentParam;
import com.fourzhang.youddit.entity.Content;
import com.fourzhang.youddit.entity.ContentImage;
import com.fourzhang.youddit.entity.ContentLabel;
import com.fourzhang.youddit.entity.Image;
import com.fourzhang.youddit.mapper.*;
import com.fourzhang.youddit.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
    @Transactional
    public Result publish(ContentParam cm) {
        //long content_id=cm.getContent_id();
        long user_id=cm.getUser_id();
        String info_describe=cm.getInfo_describe();
        LocalDateTime time=LocalDateTime.now();
        List<Long> labelids=cm.getLabel_id();
        Content content=new Content();
       // content.setContentId(content_id);
        content.setUserId(user_id);
        content.setInfoDescribe(info_describe);
        content.setPostTime(time);
        contentMapper.insert(content);

        List<String> imgurls=cm.getImage_url();
        for(String imgurl:imgurls){
            Image image=new Image();
            image.setImageUrl(imgurl);
            imageMapper.insert(image);
            ContentImage cig=new ContentImage();
            cig.setContentId(content.getContentId());
            cig.setImageId(image.getImageId());
            contentImageMapper.insert(cig);
        }


        for(long id:labelids){
            ContentLabel cl=new ContentLabel();
            cl.setContentId(content.getContentId());
            cl.setLabelId(id);
            contentLabelMapper.insert(cl);
        }

        return new Result(content);
    }
}
