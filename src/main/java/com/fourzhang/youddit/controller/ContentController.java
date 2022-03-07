package com.fourzhang.youddit.controller;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.request.ContentParam;
import com.fourzhang.youddit.response.ContentResponse;
import com.fourzhang.youddit.service.DeleteContentService;
import com.fourzhang.youddit.service.PublishService;
import com.fourzhang.youddit.service.impl.ContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/content")
public class ContentController {
    @Autowired
    private PublishService publishService;
    @Autowired
    private DeleteContentService deleteContentService;
    @Autowired
    private ContentServiceImpl contentService;

    @PostMapping("/publish")
    public Result publish(@RequestBody ContentParam contentParam){
        return publishService.publish(contentParam);
    }

    @PostMapping("/delete/{content_id}")
    public Result delete(@PathVariable long content_id){
        return deleteContentService.deletePub(content_id);
    }

    @PostMapping("/update/{content_id}")
    public Result update(@PathVariable long content_id,@RequestBody ContentParam contentParam){
        deleteContentService.deletePub(content_id);
        return publishService.publish(contentParam);
    }



    /**
     * @author zh
     * TODO: 分页查询个人发布的content
     * @param currentPage
     * @param pageSize
     * @param principal
     * @date 2022/3/6 0006 19:42
     * @return com.fourzhang.youddit.data.Result<com.fourzhang.youddit.response.ContentResponse>
     */
    @GetMapping("/getSelfPublishContents")
    public Result<ContentResponse> getSelfPublishContents(@RequestParam(required = true,value = "currentPage")long currentPage,
                                                  @RequestParam(required = true,value = "pageSize")long pageSize,
                                                  Principal principal){
        return contentService.getSelfPublishContents(currentPage, pageSize, principal);
    }
    /**
     * @author zh
     * TODO:查询他人发布的content
     * @param currentPage
     * @param pageSize
     * @param UserId
     * @date 2022/3/6 0006 20:17
     * @return com.fourzhang.youddit.data.Result<com.fourzhang.youddit.response.ContentResponse>
     */
    @GetMapping("/getOtherPublishContents")
    public Result<ContentResponse> getOtherPublishContents(@RequestParam(required = true,value = "currentPage")long currentPage,
                                                          @RequestParam(required = true,value = "pageSize")long pageSize,
                                                           @RequestParam(required = true,value = "userId")long UserId){
        return contentService.getPublishContents(currentPage, pageSize, UserId);
    }
    /**
     * @author zh
     * TODO: 查询个人收藏的帖子
     * @param currentPage
     * @param pageSize
     * @param principal
     * @date 2022/3/6 0006 20:17
     * @return com.fourzhang.youddit.data.Result<com.fourzhang.youddit.response.ContentResponse>
     */
    @GetMapping("/getSelfCollectContents")
    public Result<ContentResponse> getSelfCollectContents(@RequestParam(required = true,value = "currentPage")long currentPage,
                                                  @RequestParam(required = true,value = "pageSize")long pageSize,
                                                  Principal principal){
        return contentService.getSelfPublishContents(currentPage, pageSize, principal);
    }

}
