package com.fourzhang.youddit.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
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
    public ArrayList<PrivateMessageResponse> loadMessages(Long id1, Long id2) {

        ArrayList<PrivateMessageResponse> res = privateMessageMapper.loadMessage(id1, id2);

        return res;
    }

    @Override
    public ArrayList<Long> loadContacts(Long id) {
        QueryWrapper<PrivateMessage> wrapper = new QueryWrapper<>();
        wrapper.eq("sender_id", id);
        wrapper.or().eq("receive_id", id);

        List<PrivateMessage> list = privateMessageMapper.selectList(wrapper);

        HashSet<Long> set = new HashSet<>();

        ArrayList<Long> res = new ArrayList<>();
        for (PrivateMessage message : list) {
            Long resid;
            if (message.getSenderId().equals(id)) {
                resid = message.getReceiveId();
            }else {
                resid = message.getSenderId();
            }
            if (!set.contains(resid)) {
                res.add(resid);
                set.add(resid);
            }
        }
        return res;
    }
    
}
