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

import java.util.UUID;

@RestController
@RequestMapping("api/upload")
public class UploadController {
    @Autowired
    private QiniuUtils qiniuUtils;
    @PostMapping
    public Result upload(@RequestParam("image") MultipartFile file){
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload){
            return ResultTool.success(QiniuUtils.url + fileName);
        }
        return ResultTool.fail();
    }
}
