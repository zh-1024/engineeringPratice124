package com.fourzhang.youddit.controller;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.entity.User;
import com.fourzhang.youddit.response.ContentResponse;
import com.fourzhang.youddit.service.UserService;
import com.fourzhang.youddit.service.impl.InteractiveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * TODO:交互有关接口
 * @author zh
 * @version 1.0
 * @date 2022/3/6 000621:52
 */
@RestController
@RequestMapping("api/social")
public class InteractiveController {
    @Autowired
    InteractiveServiceImpl interactiveService;
    @Autowired
    UserService userServicel;
    @PostMapping("/likeContent")
    public Result likeContent(@RequestParam(required = true,value = "contentId")long contentId,
                              Principal principal){
        User userByName = userServicel.findUserByName(principal.getName());
        return interactiveService.likeContent(contentId, userByName.getId());
    }
    @PostMapping("/cancelLikeContent")
    public Result cancelLikeContent(@RequestParam(required = true,value = "contentId")long contentId,
                                     Principal principal){
        User userByName = userServicel.findUserByName(principal.getName());
        return interactiveService.cancelLikeContent(contentId, userByName.getId());
    }

    @GetMapping("/contentIsLiked")
    public Result<Boolean> contentIsLiked(@RequestParam(required = true,value = "contentId")long contentId,
                                   Principal principal){
        User userByName = userServicel.findUserByName(principal.getName());
        return interactiveService.contentIsLiked(contentId, userByName.getId());
    }
    @PostMapping("/collectContent")
    public Result collectContent(@RequestParam(required = true,value = "contentId")long contentId,
                                 Principal principal){
        User userByName = userServicel.findUserByName(principal.getName());
        return interactiveService.collectContent(contentId, userByName.getId());
    }
    @PostMapping("/cancelCollectContent")
    public Result cancelCollectContent(@RequestParam(required = true,value = "contentId")long contentId,
                                      Principal principal){
        User userByName = userServicel.findUserByName(principal.getName());
        return interactiveService.cancelCollectContent(contentId, userByName.getId());
    }

    @GetMapping("/contentIsCollect")
    public Result<Boolean> contentIsCollect(@RequestParam(required = true,value = "contentId")long contentId,
                                          Principal principal){
        User userByName = userServicel.findUserByName(principal.getName());
        return interactiveService.contentIsCollect(contentId, userByName.getId());
    }
    @PostMapping("/addView")
    public Result cancelCollectContent(@RequestParam(required = true,value = "contentId")long contentId){
        return interactiveService.addView(contentId);
    }


}
