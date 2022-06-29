package com.fourzhang.youddit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.entity.Content;
import com.fourzhang.youddit.entity.Label;
import com.fourzhang.youddit.mapper.ContentLabelMapper;
import com.fourzhang.youddit.mapper.ContentMapper;
import com.fourzhang.youddit.mapper.ImageMapper;
import com.fourzhang.youddit.mapper.LabelMapper;
import com.fourzhang.youddit.response.ContentResponse;
import com.fourzhang.youddit.response.LabelResponse;
import com.fourzhang.youddit.service.RecommendContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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
    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private ContentMapper contentMapper;

    /**
     * @author zh
     * TODO: 根据标签id分页查询内容列表,根据 浏览量、点赞量、收藏量进行排序
     * @param labelId
     * @param currentPage
     * @param pageSize
     * @date 2022/3/6 0006 16:54
     * @return com.fourzhang.youddit.data.Result<com.fourzhang.youddit.response.ContentResponse>
     */
    @Override
    public Result<ContentResponse> getContentsByLabel(Long labelId, Long currentPage, Long pageSize) {

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
    /**
     * @author zh
     * TODO:全部内容，根据 浏览量、点赞量、收藏量进行排序进行分页查询
     * @param currentPage
     * @param pageSize
     * @date 2022/3/6 0006 19:07
     * @return com.fourzhang.youddit.data.Result<com.fourzhang.youddit.response.ContentResponse>
     */
    @Override
    public Result<ContentResponse> getContentsAll(Long currentPage, Long pageSize) {
        QueryWrapper<Content> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("views_num","like_num","collect_num"));
        Page<Content> page = new Page<>(currentPage, pageSize);
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

    // /**
    //  * @author zh
    //  * TODO: 根据标签使用量进行排序，分页查询
    //  * @param currentPage
    //  * @param pageSize
    //  * @date 2022/3/6 0006 17:12
    //  * @return com.fourzhang.youddit.data.Result<com.fourzhang.youddit.response.LabelResponse>
    //  */
    // @Override
    // public Result<LabelResponse> getLabels(Long currentPage, Long pageSize) {
    //     QueryWrapper<Label> queryWrapper=new QueryWrapper<>();
    //     queryWrapper.orderByDesc("use_num");
    //     Page<Label> page=new Page<>(currentPage,pageSize);
    //     Page<Label> page1 = labelMapper.selectPage(page, queryWrapper);

    //     LabelResponse labelResponse = new LabelResponse(
    //             page1.getPages(),
    //             page1.getTotal(),
    //             page1.hasNext(),
    //             page1.hasPrevious(),
    //             page1.getRecords()
    //             );
    //     Result<LabelResponse> responseResult=new Result<LabelResponse>(labelResponse);
    //     return responseResult;
    // }

    /**
     * @author zh
     * TODO: 查找内容对应的图集
     * @param contentId
     * @date 2022/3/6 0006 18:41
     * @return com.fourzhang.youddit.data.Result<java.util.List<java.lang.String>>
     */
    @Override
    public Result<List<String>> getImagesByContentId(Long contentId) {
        List<String> images = imageMapper.getImageByContentId(contentId);
        Result<List<String>> result = new Result<>(images);
        return result;
    }
}
