package com.fourzhang.youddit.controller;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.ResultCode;
import com.fourzhang.youddit.data.ResultTool;
import com.fourzhang.youddit.entity.User;
import com.fourzhang.youddit.request.ResetRequest;
import com.fourzhang.youddit.request.SignUpRequest;
import com.fourzhang.youddit.request.UserInformationRequest;
import com.fourzhang.youddit.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/api/user/signup")
    public Result<Integer> signUp(@RequestBody SignUpRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        if (!userService.signUp(user)) {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
        }
        
        return ResultTool.success();
    }

    @PostMapping(path = "/api/user/reset")
    public Result<Integer> reset(@RequestBody ResetRequest request) {
        User user = userService.findUserByName(request.getUsername());
        if (user == null) { return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST); }

        if (!user.getEmail().equals(request.getEmail())) { return ResultTool.fail(ResultCode.USER_RESET_VERIFIY_FAIL); }

        if (!userService.reset(user.getUsername(), request.getNewPassword())) {
            return ResultTool.fail(ResultCode.COMMON_FAIL);
        }

        return ResultTool.success();
    }

    //获取用户的个人主页
    @GetMapping(path = "/api/user/homepage")
    public Result getUserHomePage(Principal principal){
        return userService.getUserHomePage(principal);
    }

    //获取其他用户的个人主页
    @GetMapping(path = "/api/user/visit/homepage/{username}")
    public Result getOtherUserHomePage(@PathVariable(name = "username") String username){
        return userService.getOtherUserHomePage(username);
    }

    //获取用户的个人信息修改页
    @GetMapping(path = "/api/user/information")
    public Result getUserInformationPage(Principal principal){
        return userService.getUserInformationPage(principal);
    }

    //修改个人信息修改页，暂不提供修改username的功能
    @PostMapping(path = "/api/user/information/edit")
    public Result changeUserInformationPage(@RequestBody UserInformationRequest userInformationRequest,
            Principal principal){
        return userService.changeUserInformation(userInformationRequest, principal);
    }


}