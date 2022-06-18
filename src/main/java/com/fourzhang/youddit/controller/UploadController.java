package com.fourzhang.youddit.controller;
import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.ResultTool;
import com.fourzhang.youddit.utils.QiniuUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("api/upload")
public class UploadController {
    @Autowired
    private QiniuUtils qiniuUtils;
    @PostMapping
    public Result upload(@RequestParam("image") MultipartFile file) throws IOException {
        /*
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload){
            return ResultTool.success(QiniuUtils.url + fileName);
        }
        return ResultTool.fail();*/
      //  String url=this.getClass().getClassLoader().getResource("static").getFile();
        String url=System.getProperty("user.dir")+System.getProperty("file.separator")+"src\\main\\resources\\static";

        System.out.println(url);
        String con_content=file.getOriginalFilename();
        File file1=new File(url,con_content);
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultTool.success("localhost:8080"+System.getProperty("file.separator")+con_content);
    }
}
