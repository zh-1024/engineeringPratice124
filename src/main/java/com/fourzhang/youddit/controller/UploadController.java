package com.fourzhang.youddit.controller;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.data.ResultTool;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("api/upload")
public class UploadController {
    @PostMapping
    public Result upload(@RequestPart("image") MultipartFile file) throws IOException {
        String url = System.getProperty("user.dir") + System.getProperty("file.separator")
                + "src/main/resources/static/images";

        System.out.println("Youtirsin: " + url);
        String con_content = file.getOriginalFilename();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < con_content.length(); i++) {
            if (con_content.charAt(i) != '.')
                sb.append(con_content.charAt(i));
            else
                break;
        }
        sb.append(UUID.randomUUID().toString());
        sb.append(".jpg");
        con_content = sb.toString();
        File file1 = new File(url, con_content);
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultTool.success(con_content);
    }
}
