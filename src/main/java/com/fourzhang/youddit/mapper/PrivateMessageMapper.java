package com.fourzhang.youddit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fourzhang.youddit.entity.PrivateMessage;
import com.fourzhang.youddit.response.PrivateMessageResponse;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrivateMessageMapper extends BaseMapper<PrivateMessage> {
  public ArrayList<PrivateMessageResponse> loadMessage(Long id1, Long id2);
}
