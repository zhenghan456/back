package com.zhenghan.scenery.Controller;

import lombok.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MyWebMvcConfiguration implements WebMvcConfigurer {

    //配置本地文件映射到url上
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //String uploadPathImg="C:\\Users\\admin\\IdeaProjects\\src\\main\\java\\com\\zhenghan\\scenery\\images\\";
        String uploadPathImg="src/main/java/com/zhenghan/scenery/images/";
        //重写方法
        //修改tomcat 虚拟映射
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:"+uploadPathImg);//定义图片存放路径
    }

}
