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
import com.fourzhang.youddit.request.ResetRequest;
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

    public boolean signUp(User user) {
		try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userMapper.insert(user);
		}catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
		}
        return true;
	}
    public boolean reset(ResetRequest resetRequest){
        User user=findUserByName(resetRequest.getUsername());
        //String pwd=bCryptPasswordEncoder.encode(resetRequest.getPassword());
        boolean flag=bCryptPasswordEncoder.matches(resetRequest.getPassword(),user.getPassword());
        if(flag){
            user.setPassword(bCryptPasswordEncoder.encode(resetRequest.getNewPassword()));
            user.setEmail(resetRequest.getEmail());
            userMapper.updateById(user);
            return  true;
        }
        return false;
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

    public boolean reset(String username, String password) {
        try {
            UpdateWrapper<User> wrapper = new UpdateWrapper<>();
            wrapper.eq("username", username).set("password", bCryptPasswordEncoder.encode(password));

            userMapper.update(null, wrapper);

        } catch (Exception e) {
            return false;
        }

        return true;
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
        System.out.println(from);
        System.out.println(num);
        Page<NameWithImageResponse> page = new Page<>(from, num);
        Page<NameWithImageResponse> res = userMapper.findFollowingList(id, page);
        return getResult(res);
    }

    public Result tmpFind(Long id){
        List<NameWithImageResponse> res = userMapper.tmpFind(id);
        return ResultTool.success(res);
    }


    public Result getFollowersList(Long id, Integer from, Integer num){

        Page<NameWithImageResponse> page = new Page<>(from, num);
        Page<NameWithImageResponse> res = userMapper.findFollowersList(id, page);

        return getResult(res);
    }

    private Result getResult(Page<NameWithImageResponse> res) {

        NameWithImageListResponse response = new NameWithImageListResponse();
        response.setContents(res.getRecords());
        response.setPages(res.getPages());
        response.setTotal(res.getTotal());
        response.setHasNext(res.hasNext());
        response.setHasPrevious(res.hasPrevious());

        return ResultTool.success(response);
    }
}
