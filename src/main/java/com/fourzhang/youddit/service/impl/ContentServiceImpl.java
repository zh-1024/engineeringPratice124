package com.fourzhang.youddit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.ResultTool;
import com.fourzhang.youddit.entity.*;
import com.fourzhang.youddit.entity.Image;
import com.fourzhang.youddit.entity.Label;
import com.fourzhang.youddit.mapper.*;
import com.fourzhang.youddit.response.ContentResponse;
import com.fourzhang.youddit.response.TotalContent;
import com.fourzhang.youddit.service.ContentService;
import com.fourzhang.youddit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO:此模块包含查询个人帖子、收藏帖子和他人帖子逻辑处理
 * @author zh
 * @version 1.0
 * @date 2022/3/6 000619:28
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ContentCollectUserMapper contentCollectUserMapper;
    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private ContentImageMapper contentImageMapper;
    @Autowired
    private ContentLabelMapper contentLabelMapper;
    /**
     * @author zh
     * TODO: 分页查询个人发布的content
     * @param currentPage
     * @param pageSize
     * @param principal
     * @date 2022/3/6 0006 19:54
     * @return com.fourzhang.youddit.data.Result<com.fourzhang.youddit.response.ContentResponse>
     */
    @Override
    public Result<ContentResponse> getSelfPublishContents(Long currentPage, Long pageSize, Principal principal) {
        User user = userService.findUserByName(principal.getName());
        return getPublishContents(currentPage,pageSize,user.getId());
    }
    public Result<ContentResponse> getPublishContents(Long currentPage, Long pageSize, Long userId) {
        Page<Content> page=new Page<>(currentPage,pageSize);
        QueryWrapper<Content> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.orderByDesc("post_time");
        Page<Content> res = contentMapper.selectPage(page, queryWrapper);
        ContentResponse contentResponse = new ContentResponse();
        contentResponse.setContents(res.getRecords());
        contentResponse.setPages(res.getPages());
        contentResponse.setTotal(res.getTotal());
        contentResponse.setHasNext(res.hasNext());
        contentResponse.setHasPrevious(res.hasPrevious());

        Result<ContentResponse> contentResponseResult = new Result<>(contentResponse);
        return contentResponseResult;
    }

    /**
     * @author zh
     * TODO: 分页查询个人收藏内容
     * @param currentPage
     * @param pageSize
     * @param principal
     * @date 2022/3/6 0006 19:55
     * @return com.fourzhang.youddit.data.Result<com.fourzhang.youddit.response.ContentResponse>
     */
    @Override
    public Result<ContentResponse> getSelfCollectContents(Long currentPage, Long pageSize, Principal principal) {
        User user = userService.findUserByName(principal.getName());
        Page<Content> page=new Page<>(currentPage,pageSize);
        Page<Content> res = contentCollectUserMapper.selectCollections(page, user.getId());

        ContentResponse contentResponse = new ContentResponse();
        contentResponse.setContents(res.getRecords());
        contentResponse.setPages(res.getPages());
        contentResponse.setTotal(res.getTotal());
        contentResponse.setHasNext(res.hasNext());
        contentResponse.setHasPrevious(res.hasPrevious());
        Result<ContentResponse> contentResponseResult = new Result<>(contentResponse);
        return contentResponseResult;
    }

    @Override
    public Result getLables() {
        List<String> labels=new ArrayList<>();
        List<Label> res=labelMapper.selectList(new LambdaQueryWrapper<>());
        for(Label l:res){
            labels.add(l.getLabelName());
        }
        return ResultTool.success(labels);
    }

    @Override
    public Result getContent(Long id) {
        Content content=contentMapper.selectById(id);
        TotalContent con=new TotalContent();
        con.setContentId(content.getContentId());
        con.setCollectnum(content.getCollectNum());
        con.setInfo_describe(content.getInfoDescribe());
        con.setLikenum(content.getLikeNum());
        con.setTime(content.getPostTime());
        User user=userService.findUserById(content.getUserId());
        con.setUserName(user.getUsername());
        LambdaQueryWrapper<ContentImage> wrapper=new LambdaQueryWrapper<ContentImage>();
        wrapper.eq(ContentImage::getContentId,con.getContentId());
        ContentImage ci=contentImageMapper.selectOne(wrapper);
        Long img_id=ci.getImageId();
        LambdaQueryWrapper<Image> wrapper1=new LambdaQueryWrapper<Image>();
        Image image=imageMapper.selectById(img_id);
        con.setImgUrl(image.getImageUrl());
        LambdaQueryWrapper<ContentLabel> wrapper2=new LambdaQueryWrapper<ContentLabel>();
        wrapper2.eq(ContentLabel::getContentId,con.getContentId());
        List<ContentLabel> sets=contentLabelMapper.selectList(wrapper2);
        LambdaQueryWrapper<Label> wrapper3=new LambdaQueryWrapper<>();
        List<String> labels=new ArrayList<>();
        for(ContentLabel ct:sets){
            labels.add(labelMapper.selectById(ct.getLabelId()).getLabelName());
        }
        con.setLabelNames(labels);
        return ResultTool.success(con);
    }


}
