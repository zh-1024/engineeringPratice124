package com.fourzhang.youddit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fourzhang.youddit.entity.Content;
import com.fourzhang.youddit.entity.ContentLabel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ContentLabelMapper extends BaseMapper<ContentLabel> {
    /**
     * @author zh
     * TODO: 根据标签id查询符合此标签的内容列表，按浏览量、点赞量、收藏量降序输出
     * @param page 分页对象
     * @param labelId 标签id
     * @date 2022/3/6 0006 15:36
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.fourzhang.youddit.entity.Content>
     */
    Page<Content> selectContentsByLabel(@Param("page") Page<Content> page,@Param("labelId") Long labelId);
}
