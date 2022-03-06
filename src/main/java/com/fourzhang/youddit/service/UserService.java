package com.fourzhang.youddit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.ResultCode;
import com.fourzhang.youddit.data.ResultTool;
import com.fourzhang.youddit.entity.Content;
import com.fourzhang.youddit.entity.User;
import com.fourzhang.youddit.mapper.UserMapper;

import com.fourzhang.youddit.request.PageRange;
import com.fourzhang.youddit.request.UserInformationRequest;
import com.fourzhang.youddit.response.NameWithImageListResponse;
import com.fourzhang.youddit.response.NameWithImageResponse;
import com.fourzhang.youddit.response.UserHomePageResponse;
import com.fourzhang.youddit.response.UserInformationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    public UserMapper userMapper;
    
	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("username", username);
        UserDetails details = userMapper.selectOne(wrapper);
        if (details == null) {
            throw new UsernameNotFoundException(username + " not found");
        }
        return userMapper.selectOne(wrapper);
    }

    public void signUp(User user) throws Exception {
		try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userMapper.insert(user);
		}catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
		}
	}

    public User findUserById(Long id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);

        User user = userMapper.selectOne(wrapper);

        return user;
    }

    public User findUserByName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", name);

        User user = userMapper.selectOne(wrapper);

        return user;
    }

    public Result getUserHomePage(Principal principal){
        User user = findUserByName(principal.getName());
        if (user == null) { return ResultTool.dataFail(ResultCode.COMMON_FAIL); }

        UserHomePageResponse userHomePageResponse = new UserHomePageResponse(
                user.getUsername(),
                user.getAvatar(),
                user.getFollowersNum(),
                user.getFollowingNum(),
                user.getProfile());

        return ResultTool.success(userHomePageResponse);
    }

    public Result getOtherUserHomePage(String username){
        User user = findUserByName(username);
        if (user == null) { return ResultTool.dataFail(ResultCode.COMMON_FAIL); }

        UserHomePageResponse userHomePageResponse = new UserHomePageResponse(
                user.getUsername(),
                user.getAvatar(),
                user.getFollowersNum(),
                user.getFollowingNum(),
                user.getProfile());

        return ResultTool.success(userHomePageResponse);
    }

    public Result getUserInformationPage(Principal principal){
        User user = findUserByName(principal.getName());
        if (user == null) { return ResultTool.dataFail(ResultCode.COMMON_FAIL); }

        UserInformationResponse userInformationResponse = new UserInformationResponse(
                user.getUsername(),
                user.getAvatar(),
                user.getProfile(),
                user.getEmail(),
                user.getPhone()
        );

        return ResultTool.success(userInformationResponse);
    }

    public Result changeUserInformation(UserInformationRequest userInformationRequest, Principal principal){
        if(principal.getName().equals(userInformationRequest.getUsername())){
            LambdaUpdateWrapper<User> userUpdateWrapper = new LambdaUpdateWrapper<>();
            userUpdateWrapper.set(User::getEmail, userInformationRequest.getEmail());
            userUpdateWrapper.set(User::getPhone, userInformationRequest.getPhone());
            userUpdateWrapper.set(User::getAvatar, userInformationRequest.getAvatar());
            userUpdateWrapper.set(User::getProfile, userInformationRequest.getProfile());
            userUpdateWrapper.set(User::getUpdateTime, LocalDateTime.now());
            userUpdateWrapper.eq(User::getUsername, principal.getName());
            userMapper.update(null,userUpdateWrapper);
        }else{
            return ResultTool.fail(ResultCode.USER_NO_PERMISSION);
        }
        return ResultTool.success();
    }

    public Result getFollowingList(Long id, Integer from, Integer num){
        Page<User> page = new Page<>(from, num);
        Page<User> res = userMapper.findFollowingList(id, page);

        NameWithImageListResponse response = new NameWithImageListResponse();

        List<User> userList = res.getRecords();
        List<NameWithImageResponse> resList = new ArrayList<>();
        for(User e : userList){
            NameWithImageResponse tmp = new NameWithImageResponse();
            tmp.setUsername(e.getUsername());
            tmp.setAvatar(e.getAvatar());
            resList.add(tmp);
        }

        response.setContents(resList);
        response.setPages(res.getPages());
        response.setTotal(res.getTotal());
        response.setHasNext(res.hasNext());
        response.setHasPrevious(res.hasPrevious());

        return ResultTool.success(response);
    }

}
