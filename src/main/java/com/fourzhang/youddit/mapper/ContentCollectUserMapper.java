package com.fourzhang.youddit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fourzhang.youddit.entity.Content;
import com.fourzhang.youddit.entity.ContentCollectUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ContentCollectUserMapper extends BaseMapper<ContentCollectUser> {
    /**
     * @author zh
     * @param page
     * @param UserId
     * @date 2022/3/6 0006 19:37
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.fourzhang.youddit.entity.Content>
     */
    Page<Content> selectCollections(@Param("page") Page<Content> page, @Param("UserId") Long UserId);
}
