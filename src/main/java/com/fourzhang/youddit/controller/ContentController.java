package com.fourzhang.youddit.controller;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.ResultTool;
import com.fourzhang.youddit.request.ContentParam;
import com.fourzhang.youddit.response.ContentResponse;
import com.fourzhang.youddit.service.DeleteContentService;
import com.fourzhang.youddit.service.PublishService;
import com.fourzhang.youddit.service.impl.ContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Random;

@RestController
@RequestMapping("api/content")
public class ContentController {
    @Autowired
    private PublishService publishService;
    @Autowired
    private DeleteContentService deleteContentService;
    @Autowired
    private ContentServiceImpl contentService;
    //返回一个随机的头像
    @GetMapping("/avatar")
    public Result getAvatar(){
        Random random=new Random();
        int x=random.nextInt(5);
        String url="localhost:8080"+System.getProperty("file.separator");
        switch (x){
            case 0:
                url+="img.png";
                break;
            case 1:
                url+="img_1.png";
                break;
            case 2:
                url+="img_2.png";
                break;
            case 3:
                url+="img_3.png";
            case 4:
                url+="img_4.png";
            default:
                break;
        }
        return ResultTool.success(url);
    }
    //发布内容，包含发布得相关信息以及图片
    @PostMapping("/publish")
    public Result publish(@RequestBody ContentParam contentParam,@RequestParam("image") MultipartFile file, Principal principal){
        String url=System.getProperty("user.dir")+System.getProperty("file.separator")+"src\\main\\resources\\static";

        System.out.println(url);
        String con_content=file.getOriginalFilename();
        File file1=new File(url,con_content);
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        publishService.publish(contentParam,principal);
        return ResultTool.success("localhost:8080"+System.getProperty("file.separator")+con_content);
    }

    @PostMapping("/delete/{content_id}")
    public Result delete(@PathVariable long content_id){
        return deleteContentService.deletePub(content_id);
    }

    @PostMapping("/update/{content_id}")
    public Result update(@PathVariable long content_id,@RequestBody ContentParam contentParam,Principal principal){
        deleteContentService.deletePub(content_id);
        return publishService.publish(contentParam,principal);
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

    @GetMapping("/getlables")
    public Result getLables(){
        return contentService.getLables();
    }

}
