package com.fourzhang.youddit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.ResultTool;
import com.fourzhang.youddit.request.ContentParam;
import com.fourzhang.youddit.entity.*;
import com.fourzhang.youddit.mapper.*;
import com.fourzhang.youddit.service.PublishService;
import com.fourzhang.youddit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


@Service
public class PublishServiceImpl implements PublishService {
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private ContentLabelMapper contentLabelMapper;
    @Autowired
    private ContentImageMapper contentImageMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public Result publish(ContentParam cm,Principal principal) {
        System.out.println(principal.getName());
        long user_id=userService.findUserByName(principal.getName()).getId();
        /*令牌桶算法的限频处理*/
        /*
        String key=user_id+"::publish";
        if(redisTemplate.opsForValue().get(key)==null){
            redisTemplate.opsForValue().setIfAbsent(key,"10",60L, TimeUnit.SECONDS);
            Timer timer=new Timer();
            TimerTask task=new TimerTask() {
                @Override
                public void run() {
                    String val=redisTemplate.opsForValue().get(key);
                    if(val.isEmpty()) this.cancel();
                    if(Integer.parseInt(val)<10)
                        redisTemplate.opsForValue().increment(key);
                }
            };
            timer.schedule(task,0,6000);
        }else{
            if(Integer.parseInt(redisTemplate.opsForValue().get(key))<1){
                return ResultTool.fail();
            }else{
                redisTemplate.opsForValue().decrement(key);
            }
        }
    */

        String info_describe=cm.getInfo_describe();
        LocalDateTime time=LocalDateTime.now();
        List<String> labelnames=cm.getLabel_names();
        Content content=new Content();
        content.setUserId(user_id);
        content.setInfoDescribe(info_describe);
        content.setPostTime(time);
        contentMapper.insert(content);
        String imgurl=cm.getImage_url();
        Image image=new Image();
        image.setImageUrl(imgurl);
        imageMapper.insert(image);
        ContentImage cig=new ContentImage();
        cig.setContentId(content.getContentId());
        cig.setImageId(image.getImageId());
        contentImageMapper.insert(cig);

        LambdaQueryWrapper<Label> wrapper=new LambdaQueryWrapper<>();
        for(String name:labelnames){
            ContentLabel cl=new ContentLabel();
            cl.setContentId(content.getContentId());
            wrapper.eq(Label::getLabelName,name);
            Label label=labelMapper.selectOne(wrapper);
            if(label==null){
                label=new Label();
                label.setUseNum(1L);
                label.setLabelName(name);
                labelMapper.insert(label);
                System.out.println("标签"+label.getLabelName()+"为新，添加");
            }else{
                label.setUseNum(label.getUseNum()+1);
                labelMapper.updateById(label);
                System.out.println("标签"+label.getLabelName()+"已存在，引用数加一");
            }
            cl.setLabelId(label.getLabelId());
            contentLabelMapper.insert(cl);
            wrapper.clear();
        }
        System.out.println(content.getContentId());
        return ResultTool.success(content.getContentId());
    }


    /*
    @Override
    public Result upDateContent(ContentParam contentParam, Principal principal,Long content_id) {
        String name=principal.getName();
        LambdaQueryWrapper<User> wq=new LambdaQueryWrapper<>();
        wq.eq(User::getUsername,name);
        wq.select(User::getId);
        User user=userMapper.selectOne(wq);
        Content content=contentMapper.selectById(content_id);
        content.setUserId(user.getId());
        content.setInfoDescribe(contentParam.getInfo_describe());
        content.setPostTime(LocalDateTime.now());
        return null;
    }

     */

}
