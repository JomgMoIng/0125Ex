package org.spring.springbootjpareply.config;

import lombok.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // 스프링 설정           // 외부에서 로컬의 특정 폴더에 접근
public class WebConfigClass implements WebMvcConfigurer {


//    @Value("${filePath}")
        String saveFiles="file:///C:/saveFiles/";   // 외부에서 접근 허용할 폴더경로 설정

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 요청 URL
        registry.addResourceHandler("upload/**")
                .addResourceLocations(saveFiles);   // 폴더 접근 권한 허용
    }

}
