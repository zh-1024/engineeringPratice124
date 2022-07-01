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

    // 发布内容，包含发布得相关信息以及图片
    @PostMapping("/publish")
    public Result publish(@RequestBody ContentParam contentParam, Principal principal) {
        /*
         * String
         * url=System.getProperty("user.dir")+System.getProperty("file.separator")+
         * "src\\main\\resources\\static";
         * 
         * System.out.println(url);
         * String con_content=file.getOriginalFilename();
         * con_content+= UUID.randomUUID().toString();
         * File file1=new File(url,con_content);
         * try {
         * file.transferTo(file1);
         * } catch (IOException e) {
         * e.printStackTrace();
         * }
         * 
         */
        // contentParam.setImage_url(url);
        return publishService.publish(contentParam, principal);

        // return
        // ResultTool.success("localhost:8080"+System.getProperty("file.separator")+con_content);
        // return ResultTool.success(con_content);
    }

    @GetMapping("/getContent")
    public Result getContentById(@RequestParam("id") Long id) {
        return contentService.getContent(id);
    }

    @PostMapping("/delete/{content_id}")
    public Result delete(@PathVariable long content_id) {
        return deleteContentService.deletePub(content_id);
    }

    @PostMapping("/update/{content_id}")
    public Result update(@PathVariable long content_id, @RequestBody ContentParam contentParam, Principal principal) {
        deleteContentService.deletePub(content_id);
        return publishService.publish(contentParam, principal);
    }

    /**
     * @author zh
     * @param currentPage
     * @param pageSize
     * @param principal
     * @date 2022/3/6 0006 19:42
     * @return com.fourzhang.youddit.data.Result<com.fourzhang.youddit.response.ContentResponse>
     */
    @GetMapping("/getSelfPublishContents")
    public Result<ContentResponse> getSelfPublishContents(
            @RequestParam(required = true, value = "currentPage") long currentPage,
            @RequestParam(required = true, value = "pageSize") long pageSize,
            Principal principal) {
        return contentService.getSelfPublishContents(currentPage, pageSize, principal);
    }

    /**
     * @author zh
     * @param currentPage
     * @param pageSize
     * @param UserId
     * @date 2022/3/6 0006 20:17
     * @return com.fourzhang.youddit.data.Result<com.fourzhang.youddit.response.ContentResponse>
     */
    @GetMapping("/getOtherPublishContents")
    public Result<ContentResponse> getOtherPublishContents(
            @RequestParam(required = true, value = "currentPage") long currentPage,
            @RequestParam(required = true, value = "pageSize") long pageSize,
            @RequestParam(required = true, value = "userId") long UserId) {
        return contentService.getPublishContents(currentPage, pageSize, UserId);
    }

    /**
     * @author zh
     * @param currentPage
     * @param pageSize
     * @param principal
     * @date 2022/3/6 0006 20:17
     * @return com.fourzhang.youddit.data.Result<com.fourzhang.youddit.response.ContentResponse>
     */
    @GetMapping("/getSelfCollectContents")
    public Result<ContentResponse> getSelfCollectContents(
            @RequestParam(required = true, value = "currentPage") long currentPage,
            @RequestParam(required = true, value = "pageSize") long pageSize,
            Principal principal) {
        return contentService.getSelfPublishContents(currentPage, pageSize, principal);
    }

    @GetMapping("/getlabels")
    public Result getLabels() {
        return contentService.getLabels();
    }
}
