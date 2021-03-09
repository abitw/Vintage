package tw.leader.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class UploadConfig extends WebMvcConfigurerAdapter{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//圖片保存地址
        registry.addResourceHandler("/img/**").addResourceLocations("file:D:\\springboot\\Git3\\Vintage\\src\\main\\resources\\static\\img\\");
    }
}
