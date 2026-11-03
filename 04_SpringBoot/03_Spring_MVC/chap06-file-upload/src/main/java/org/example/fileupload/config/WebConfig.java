package org.example.fileupload.config;

/*
* WebMvcConfigurer
* - Spring MVC의 기본 설정을 유지하면서 필요한 기능만 추가하는 인터페이스
* - 정적 리소스, CORS, 인터셉터 등 웹 설정을 재정의 가능
* */

import org.example.fileupload.util.FileUploadUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final FileUploadUtils fileUploadUtils;

    public WebConfig(FileUploadUtils fileUploadUtils) {
        this.fileUploadUtils = fileUploadUtils;
    }

    /*
    * addResourceHandlers
    * - URL 패턴(/uploads/**)과 실제 저장 경로(file:...)를 연결한다.
    * - 브라우저가 /uploads/img/single/xxx.jpeg 를 요청하면
    *   외부 폴더 <basePath>/img/single/xxx.jpeg 파일을 돌려준다.
    * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + fileUploadUtils.getBasePath() + "/");
    }
}
