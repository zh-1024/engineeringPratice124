package com.fourzhang.youddit.controller;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.entity.User;
import com.fourzhang.youddit.service.UserService;
import com.fourzhang.youddit.service.impl.InteractiveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/social")
public class InteractiveController {
    @Autowired
    InteractiveServiceImpl interactiveService;
    @Autowired
    UserService userServicel;

    @GetMapping("/togglelike")
    public Result togglelike(@RequestParam(required = true) long contentId, Principal principal) {
        User user = userServicel.findUserByName(principal.getName());
        return interactiveService.togglelike(contentId, user.getId());
    }

    @GetMapping("/isliked")
    public Result isliked(@RequestParam(required = true) long contentId, Principal principal) {
        User user = userServicel.findUserByName(principal.getName());
        return interactiveService.isLiked(contentId, user.getId());
    }

    @GetMapping("/togglecollect")
    public Result togglecollect(@RequestParam(required = true) long contentId, Principal principal) {
        User user = userServicel.findUserByName(principal.getName());
        return interactiveService.toggleCollect(contentId, user.getId());
    }

    @GetMapping("/iscollected")
    public Result iscollected(@RequestParam(required = true) long contentId, Principal principal) {
        User user = userServicel.findUserByName(principal.getName());
        return interactiveService.isCollected(contentId, user.getId());
    }

    @PostMapping("/addView")
    public Result cancelCollectContent(@RequestParam(required = true, value = "contentId") long contentId) {
        return interactiveService.addView(contentId);
    }

}
