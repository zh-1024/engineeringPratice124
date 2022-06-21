//package com.zh.music.config;
package com.fourzhang.youddit.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebMVCConfig
 * @Date 2021/10/13 22:34
 * @Author zhang hui
 * @Description 解决跨域问题
 */

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("**")    //哪些网站可以访问
                .allowedMethods("*")    //哪些方法可以访问
                .allowCredentials(true); //访问时是否需要验证
    }
}
