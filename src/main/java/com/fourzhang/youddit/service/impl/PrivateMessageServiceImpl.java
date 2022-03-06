package com.fourzhang.youddit.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fourzhang.youddit.entity.PrivateMessage;
import com.fourzhang.youddit.mapper.PrivateMessageMapper;
import com.fourzhang.youddit.response.PrivateMessageResponse;
import com.fourzhang.youddit.service.PrivateMessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivateMessageServiceImpl implements PrivateMessageService {

    @Autowired
    private PrivateMessageMapper privateMessageMapper;

    @Override
    public boolean saveMessage(PrivateMessage message) {
        try {
            privateMessageMapper.insert(message);
            
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public ArrayList<PrivateMessageResponse> loadMessage(Long id) {
        QueryWrapper<PrivateMessage> wrapper = new QueryWrapper<>();
        wrapper.eq("senderId", id);
        wrapper.or().eq("receiveId", id);

        List<PrivateMessage> list = privateMessageMapper.selectList(wrapper);

        ArrayList<PrivateMessageResponse> res = new ArrayList<>();
        for (PrivateMessage message : list) {
            res.add(new PrivateMessageResponse(message.getSenderId(), message.getMessage()));
        }

        return res;
    }
    
}
