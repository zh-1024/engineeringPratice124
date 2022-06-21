package com.fourzhang.youddit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyWebConfig extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")    // 虚拟路径
                // file: 表示以本地的路径方式去访问绝对路径。
                .addResourceLocations("file:E:\\jetbrains\\engineeringPratice124\\src\\main\\resources\\static\\images\\");    // 绝对路径
    }


}
