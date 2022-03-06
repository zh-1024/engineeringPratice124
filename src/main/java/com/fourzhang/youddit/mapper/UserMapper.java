package com.fourzhang.youddit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fourzhang.youddit.entity.Content;
import com.fourzhang.youddit.entity.User;
import com.fourzhang.youddit.response.NameWithImageResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    Page<User> findFollowingList(Long id, Page<User> page);
}
