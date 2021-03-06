package com.fourzhang.youddit.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.ResultCode;
import com.fourzhang.youddit.data.ResultTool;
import com.fourzhang.youddit.entity.PrivateMessage;
import com.fourzhang.youddit.entity.User;
import com.fourzhang.youddit.request.PrivateMessageRequest;
import com.fourzhang.youddit.response.PrivateMessageResponse;
import com.fourzhang.youddit.service.PrivateMessageService;
import com.fourzhang.youddit.service.UserService;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateMessageController {

    @Autowired
    private PrivateMessageService privateMessageService;

    @Autowired
    private UserService userService;

    @Autowired
    private DirectExchange direct;

    @Autowired
    private RabbitTemplate template;

    @GetMapping("/chat/loadcontacts")
    public Result<ArrayList<Long>> loadContacts(Principal principal) {
        User user = userService.findUserByName(principal.getName());
        if (user == null) {
            return ResultTool.dataFail(ResultCode.COMMON_FAIL);
        }

        ArrayList<Long> res = privateMessageService.loadContacts(user.getId());
        return ResultTool.success(res);
    }

    @GetMapping("/chat/loadmessages")
    public Result<ArrayList<PrivateMessageResponse>> loadMessages(Long contactId, Principal principal) {
        User user = userService.findUserByName(principal.getName());
        if (user == null) {
            return ResultTool.dataFail(ResultCode.COMMON_FAIL);
        }

        ArrayList<PrivateMessageResponse> res = privateMessageService.loadMessages(user.getId(), contactId);

        return ResultTool.success(res);
    }

    @GetMapping("/chat/send")
    public Result<Integer> sendMessage(@RequestParam("receiveId") Long receiveId,
            @RequestParam("message") String msg, Principal principal) {
        User receiver = userService.findUserById(receiveId);
        if (receiver == null) {
            return ResultTool.fail();
        }
        User sender = userService.findUserByName(principal.getName());
        if (sender == null) {
            return ResultTool.fail();
        }
        PrivateMessage message = new PrivateMessage();
        message.setSenderId(sender.getId());
        message.setReceiveId(receiver.getId());
        message.setMessage(msg);
        message.setSendTime(LocalDateTime.now());
        if (!privateMessageService.saveMessage(message)) {
            return ResultTool.fail();
        }
        PrivateMessageResponse response = new PrivateMessageResponse(message.getSenderId(), message.getMessage(),
                message.getSendTime());
        try {
            template.convertAndSend(direct.getName(), message.getReceiveId().toString(), JSON.toJSONString(response));
        } catch (Exception e) {
            return ResultTool.fail();
        }
        return ResultTool.success();
    }
}
