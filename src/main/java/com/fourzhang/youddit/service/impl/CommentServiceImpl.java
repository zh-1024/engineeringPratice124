package com.fourzhang.youddit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.ResultTool;
import com.fourzhang.youddit.entity.CommentComment;
import com.fourzhang.youddit.entity.ContentComment;
import com.fourzhang.youddit.entity.User;
import com.fourzhang.youddit.mapper.CommentCommentMapper;
import com.fourzhang.youddit.mapper.ContentCommentMapper;
import com.fourzhang.youddit.mapper.UserMapper;
import com.fourzhang.youddit.request.CommentRequest;
import com.fourzhang.youddit.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentCommentMapper commentCommentMapper;
    @Autowired
    private ContentCommentMapper contentCommentMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    @Transactional
    public Result commentComment(CommentRequest commentRequest,String usrname) {
        CommentComment commentComment=new CommentComment();
        //BeanUtils.copyProperties(commentRequest,commentComment);
        commentComment.setContentCommentId(commentRequest.getContentCommentId());
        commentComment.setCommentTime(LocalDateTime.now());
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.select(User::getId);
        wrapper.eq(User::getUsername,usrname);
        User usr=userMapper.selectOne(wrapper);
        commentComment.setUserId(usr.getId());
        if(commentRequest.getLevel()==0){
            commentComment.setLevel(0);
        }else if(commentRequest.getLevel()==1){
            commentComment.setLevel(1);
            commentComment.setCommentCommentId(commentRequest.getCommentCommentId());
        }else{
            return ResultTool.fail();
        }
        commentCommentMapper.insert(commentComment);
        return ResultTool.success(commentComment);
    }

    @Override
    public Result contentComment(CommentRequest commentRequest, String usrname) {
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.select(User::getId);
        wrapper.eq(User::getUsername,usrname);
        User usr=userMapper.selectOne(wrapper);
        Long usrid=usr.getId();
        ContentComment contentComment=new ContentComment();
        contentComment.setUserId(usrid);
        contentComment.setCommentTime(LocalDateTime.now());
        contentComment.setCommentInfo(commentRequest.getComment_info());
        contentComment.setContentId(commentRequest.getContentId());
        contentCommentMapper.insert(contentComment);
        return ResultTool.success(contentComment);
    }



    @Override
    public List<ContentComment> loadContentComment(Long contentId, Integer from, Integer num) {
        QueryWrapper<ContentComment> wrapper = new QueryWrapper<>();
        wrapper.eq("content_id", contentId);

        Page<ContentComment> page = new Page<>(from, num);
        Page<ContentComment> res = contentCommentMapper.selectPage(page, wrapper);

        return res.getRecords();
    }

    @Override
    public List<CommentComment> loadCommentComment(Long commentId, Integer from, Integer num) {
        QueryWrapper<CommentComment> wrapper = new QueryWrapper<>();
        wrapper.eq("content_comment_id", commentId);

        Page<CommentComment> page = new Page<>(from, num);
        Page<CommentComment> res = commentCommentMapper.selectPage(page, wrapper);

        return res.getRecords();
    }
}
