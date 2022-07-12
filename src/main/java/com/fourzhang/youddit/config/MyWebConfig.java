package com.fourzhang.youddit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class MyWebConfig extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String imgPath = System.getProperty("user.dir") + "/src/main/resources/files/images/";
        String avatarPath = System.getProperty("user.dir") + "/src/main/resources/files/avatars/";

        registry.addResourceHandler("/images/**")    // virtual path
                .addResourceLocations("file:" + imgPath);
        registry.addResourceHandler("/avatars/**")
                .addResourceLocations("file:" + avatarPath);
    }

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);
//        MappingJackson2HttpMessageConverter jk=new MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper=jk.getObjectMapper();
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        jk.setObjectMapper(objectMapper);

    }
}
