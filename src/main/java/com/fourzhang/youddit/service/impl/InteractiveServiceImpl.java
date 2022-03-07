package com.fourzhang.youddit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.ResultCode;
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

/**
 * TODO 交互相关业务逻辑实现
 * @author zh
 * @version 1.0
 * @date 2022/3/6 000620:47
 */
@Service
public class InteractiveServiceImpl implements InteractiveService {

    @Autowired
    private ContentLikeUserMapper contentLikeUserMapper;
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private ContentCollectUserMapper contentCollectUserMapper;
    @Override
    @Transactional
    public Result likeContent(Long contentId, Long userId) {
        Result<Boolean> booleanResult = contentIsLiked(contentId, userId);
        if(booleanResult.getData().equals(true))
            return  new Result(ResultCode.CONTENT_IS_LIKED);
        ContentLikeUser contentLikeUser = new ContentLikeUser();
        contentLikeUser.setContentId(contentId);
        contentLikeUser.setUserId(userId);
        contentIsLiked(contentId,userId);
        contentLikeUserMapper.insert(contentLikeUser);

        Content content = contentMapper.selectById(contentId);
        content.setLikeNum(content.getLikeNum()+1);
        int update = contentMapper.updateById(content);
        return new Result(ResultCode.SUCCESS);
    }
    @Override
    @Transactional
    public Result cancelLikeContent(Long contentId, Long userId) {
        Result<Boolean> booleanResult = contentIsLiked(contentId, userId);
        if(booleanResult.getData().equals(false))
            return  new Result(ResultCode.CONTENT_CANCEL_LIKED);
        QueryWrapper<ContentLikeUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("content_id",contentId);
        queryWrapper.eq("user_id",userId);
        contentLikeUserMapper.delete(queryWrapper);

        Content content = contentMapper.selectById(contentId);
        content.setLikeNum(content.getLikeNum()-1);
        int update = contentMapper.updateById(content);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    @Transactional
    public Result<Boolean> contentIsLiked(Long contentId, Long userId) {
        QueryWrapper<ContentLikeUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("content_id",contentId);
        queryWrapper.eq("user_id",userId);
        List<ContentLikeUser> contentLikeUsers = contentLikeUserMapper.selectList(queryWrapper);
        if(contentLikeUsers.isEmpty())
            return new Result<Boolean>(false);
        else
            return new Result<Boolean>(true);
    }

    @Override
    @Transactional
    public Result collectContent(Long contentId, Long userId) {
        Result<Boolean> booleanResult = contentIsCollect(contentId, userId);
        if(booleanResult.getData().equals(true))
            return  new Result(ResultCode.CONTENT_IS_COLLECTED);
        ContentCollectUser contentCollectUser=new ContentCollectUser();
        contentCollectUser.setContentId(contentId);
        contentCollectUser.setUserId(userId);
        contentCollectUserMapper.insert(contentCollectUser);

        Content content = contentMapper.selectById(contentId);
        content.setCollectNum(content.getCollectNum()+1);

        int update = contentMapper.updateById(content);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    @Transactional
    public Result cancelCollectContent(Long contentId, Long userId) {
        Result<Boolean> booleanResult = contentIsCollect(contentId, userId);
        if(booleanResult.getData().equals(false))
            return  new Result(ResultCode.CONTENT_CANCEL_COLLECTED);

        QueryWrapper<ContentCollectUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("content_id",contentId);
        queryWrapper.eq("user_id",userId);
        contentCollectUserMapper.delete(queryWrapper);

        Content content = contentMapper.selectById(contentId);
        content.setCollectNum(content.getLikeNum()-1);

        int update = contentMapper.updateById(content);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result<Boolean> contentIsCollect(Long contentId, Long userId) {
        QueryWrapper<ContentCollectUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("content_id",contentId);
        queryWrapper.eq("user_id",userId);
        List<ContentCollectUser> contentCollectUsers = contentCollectUserMapper.selectList(queryWrapper);
        if(contentCollectUsers.isEmpty())
            return new Result<Boolean>(false);
        else
            return new Result<Boolean>(true);
    }

    @Override
    public Result addView(Long contentId) {
        Content content = contentMapper.selectById(contentId);
        content.setViewsNum(content.getViewsNum()+1);
        int update = contentMapper.updateById(content);
        return new Result(ResultCode.SUCCESS);
    }
}
