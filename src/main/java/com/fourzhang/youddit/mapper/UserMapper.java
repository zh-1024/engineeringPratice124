package com.fourzhang.youddit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fourzhang.youddit.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    
}
