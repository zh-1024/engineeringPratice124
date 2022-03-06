package com.fourzhang.youddit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.ResultTool;
import com.fourzhang.youddit.entity.*;
import com.fourzhang.youddit.mapper.*;
import com.fourzhang.youddit.service.DeleteContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeleteContentServiceImpl implements DeleteContentService {
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
    private ContentLikeUserMapper contentLikeUserMapper;
    @Autowired
    private CommentCommentMapper commentCommentMapper;
    @Autowired
    private ContentCommentMapper contentCommentMapper;
    @Autowired
    private ContentCollectUserMapper contentCollectUserMapper;
    @Override
    @Transactional
    public Result deletePub(long content_id) {
        //删除ContentLabel
        LambdaQueryWrapper<ContentLabel> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ContentLabel::getContentId,content_id);
        queryWrapper.select(ContentLabel::getLabelId);
        List<ContentLabel> labelids=contentLabelMapper.selectList(queryWrapper);
        int numbers=contentLabelMapper.delete(queryWrapper);
        System.out.println("删除了"+numbers+"条ContentLabel");
        queryWrapper.clear();
        //删除Label
        LambdaQueryWrapper<Label> queryWrapper1=new LambdaQueryWrapper<>();
        for(ContentLabel conlabel:labelids){
            long id=conlabel.getContentId();
            // queryWrapper1.eq(Label::getLabelId,id);
            Label label=labelMapper.selectById(id);
            label.setUseNum(label.getUseNum()-1);
            if(label.getUseNum()==0){
                labelMapper.deleteById(label);
            }else{
                labelMapper.updateById(label);
            }
        }

        //删除content_image
        LambdaQueryWrapper<ContentImage> queryWrapper3=new LambdaQueryWrapper<>();
        queryWrapper3.eq(ContentImage::getContentId,content_id);
        queryWrapper3.select(ContentImage::getImageId);
        List<ContentImage> images=contentImageMapper.selectList(queryWrapper3);
        numbers=contentImageMapper.delete(queryWrapper3);
        System.out.println("删除了"+numbers+"条ContentImage");

        //删除Image
        for(ContentImage image:images){
            long id=image.getImageId();
            Image im=imageMapper.selectById(id);
            imageMapper.deleteById(im);
        }

        //删除content_like_user
        LambdaQueryWrapper<ContentLikeUser> queryWrapper4=new LambdaQueryWrapper<>();
        queryWrapper4.eq(ContentLikeUser::getContentId,content_id);
        contentLikeUserMapper.delete(queryWrapper4);

        //删除content_collect_user
        LambdaQueryWrapper<ContentCollectUser> queryWrapper5=new LambdaQueryWrapper<>();
        queryWrapper5.eq(ContentCollectUser::getContentId,content_id);
        contentCollectUserMapper.delete(queryWrapper5);

        //删除content_comment
        LambdaQueryWrapper<ContentComment> queryWrapper6=new LambdaQueryWrapper<>();
        queryWrapper6.eq(ContentComment::getContentId,content_id);
        contentCommentMapper.delete(queryWrapper6);

        //删除comment_comment
        LambdaQueryWrapper<CommentComment> queryWrapper7=new LambdaQueryWrapper<>();
        queryWrapper7.eq(CommentComment::getCommentCommentId,content_id);
        commentCommentMapper.delete(queryWrapper7);
        //删除内容
        contentMapper.deleteById(content_id);
        return ResultTool.success(null);
    }
}
