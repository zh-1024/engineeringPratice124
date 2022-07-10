package com.fourzhang.youddit.service;

import java.util.ArrayList;

import com.fourzhang.youddit.entity.PrivateMessage;
import com.fourzhang.youddit.response.PrivateMessageResponse;

public interface PrivateMessageService {
    boolean saveMessage(PrivateMessage message);

    ArrayList<PrivateMessageResponse> loadMessages(Long id1, Long id2);

    ArrayList<Long> loadContacts(Long id);
}
