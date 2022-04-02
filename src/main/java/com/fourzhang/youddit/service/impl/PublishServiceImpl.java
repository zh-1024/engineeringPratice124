package com.fourzhang.youddit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.ResultTool;
import com.fourzhang.youddit.request.ContentParam;
import com.fourzhang.youddit.entity.*;
import com.fourzhang.youddit.mapper.*;
import com.fourzhang.youddit.service.PublishService;
import com.fourzhang.youddit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
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
    @Autowired
    private UserMapper userMapper;
    @Override
    @Transactional
    public Result publish(ContentParam cm) {
        long user_id=cm.getUser_id();
        String info_describe=cm.getInfo_describe();
        LocalDateTime time=LocalDateTime.now();
        List<String> labelnames=cm.getLabel_names();
        Content content=new Content();
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
        LambdaQueryWrapper<Label> wrapper=new LambdaQueryWrapper<>();
        for(String name:labelnames){
            ContentLabel cl=new ContentLabel();
            cl.setContentId(content.getContentId());
            wrapper.eq(Label::getLabelName,name);
            Label label=labelMapper.selectOne(wrapper);
            if(label==null){
                label=new Label();
                label.setUseNum(0L);
                label.setLabelName(name);
                labelMapper.insert(label);
            }else{
                label.setUseNum(label.getUseNum()+1);
                labelMapper.updateById(label);
            }
            cl.setLabelId(label.getLabelId());
            contentLabelMapper.insert(cl);
        }
        System.out.println(content.getContentId());
        return ResultTool.success(content.getContentId());
    }
    /*
    @Override
    public Result upDateContent(ContentParam contentParam, Principal principal,Long content_id) {
        String name=principal.getName();
        LambdaQueryWrapper<User> wq=new LambdaQueryWrapper<>();
        wq.eq(User::getUsername,name);
        wq.select(User::getId);
        User user=userMapper.selectOne(wq);
        Content content=contentMapper.selectById(content_id);
        content.setUserId(user.getId());
        content.setInfoDescribe(contentParam.getInfo_describe());
        content.setPostTime(LocalDateTime.now());
        return null;
    }

     */

}
