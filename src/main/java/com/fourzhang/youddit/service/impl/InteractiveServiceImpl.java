package com.fourzhang.youddit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.ResultCode;
import com.fourzhang.youddit.data.ResultTool;
import com.fourzhang.youddit.entity.Content;
import com.fourzhang.youddit.entity.ContentCollectUser;
import com.fourzhang.youddit.entity.ContentLikeUser;
import com.fourzhang.youddit.mapper.ContentCollectUserMapper;
import com.fourzhang.youddit.mapper.ContentLikeUserMapper;
import com.fourzhang.youddit.mapper.ContentMapper;
import com.fourzhang.youddit.service.InteractiveService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InteractiveServiceImpl implements InteractiveService {

    @Autowired
    private ContentLikeUserMapper contentLikeUserMapper;
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private ContentCollectUserMapper contentCollectUserMapper;

    @Transactional
    @Override
    public Result togglelike(Long contentId, Long userId) {
        QueryWrapper<ContentLikeUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("content_id", contentId);
        queryWrapper.eq("user_id", userId);
        List<ContentLikeUser> contentLikeUsers = contentLikeUserMapper.selectList(queryWrapper);
        if (contentLikeUsers.isEmpty()) {
            ContentLikeUser contentLikeUser = new ContentLikeUser();
            contentLikeUser.setContentId(contentId);
            contentLikeUser.setUserId(userId);
            contentLikeUserMapper.insert(contentLikeUser);
            // update like num
            Content content = contentMapper.selectById(contentId);
            content.setLikeNum(content.getLikeNum() + 1);
            contentMapper.updateById(content);
            return ResultTool.success(true);
        } else {
            contentLikeUserMapper.delete(queryWrapper);
            // update like num
            Content content = contentMapper.selectById(contentId);
            content.setLikeNum(content.getLikeNum() - 1);
            contentMapper.updateById(content);
            return ResultTool.success(false);
        }
    }

    @Override
    public Result isLiked(Long contentId, Long userId) {
        QueryWrapper<ContentLikeUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("content_id", contentId);
        queryWrapper.eq("user_id", userId);
        List<ContentLikeUser> contentLikeUsers = contentLikeUserMapper.selectList(queryWrapper);
        return ResultTool.success(!contentLikeUsers.isEmpty());
    }

    @Override
    public Result toggleCollect(Long contentId, Long userId) {
        QueryWrapper<ContentCollectUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("content_id", contentId);
        queryWrapper.eq("user_id", userId);
        List<ContentCollectUser> contentCollectUsers = contentCollectUserMapper.selectList(queryWrapper);
        if (contentCollectUsers.isEmpty()) {
            ContentCollectUser contentCollectUser = new ContentCollectUser();
            contentCollectUser.setContentId(contentId);
            contentCollectUser.setUserId(userId);
            contentCollectUserMapper.insert(contentCollectUser);
            // update collect num
            Content content = contentMapper.selectById(contentId);
            content.setCollectNum(content.getCollectNum() + 1);
            contentMapper.updateById(content);
            return ResultTool.success(true);
        } else {
            contentCollectUserMapper.delete(queryWrapper);
            Content content = contentMapper.selectById(contentId);
            content.setCollectNum(content.getLikeNum() - 1);
            contentMapper.updateById(content);
            return ResultTool.success(false);
        }
    }

    @Override
    public Result isCollected(Long contentId, Long userId) {
        QueryWrapper<ContentCollectUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("content_id", contentId);
        queryWrapper.eq("user_id", userId);
        List<ContentCollectUser> contentCollectUsers = contentCollectUserMapper.selectList(queryWrapper);
        return ResultTool.success(!contentCollectUsers.isEmpty());
    }

    @Override
    public Result addView(Long contentId) {
        Content content = contentMapper.selectById(contentId);
        content.setViewsNum(content.getViewsNum() + 1);
        contentMapper.updateById(content);
        return new Result(ResultCode.SUCCESS);
    }
}
