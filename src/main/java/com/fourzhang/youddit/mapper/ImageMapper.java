package com.fourzhang.youddit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fourzhang.youddit.entity.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImageMapper extends BaseMapper<Image> {
    public List<String> getImageByContentId(@Param("contentId") Long contentId);
}
