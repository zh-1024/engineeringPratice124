package com.fourzhang.youddit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fourzhang.youddit.entity.User;
import com.fourzhang.youddit.response.NameWithImageResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    Page<NameWithImageResponse> findFollowingList(@Param("id")Long id, @Param("page") Page<NameWithImageResponse> page);
    Page<NameWithImageResponse> findFollowersList(Long id, Page<NameWithImageResponse> page);
//    List<NameWithImageResponse> tmpFind(Long id);
}
