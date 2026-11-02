package org.example.filterandintercepter.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// WebMvcConfigurer : SpringMVC 설정 (CORS, Interceptor, URL mapping etc) 개발자가 커스텀할 수 있게 해주는 인터페이스
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private final StopWatchInterceptor stopWatchInterceptor;

    public WebConfiguration(StopWatchInterceptor stopWatchInterceptor) {
        this.stopWatchInterceptor = stopWatchInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //InterceptorRegistry: Spring MVC에서 관리하는 등록 객체
        registry.addInterceptor(stopWatchInterceptor)
                .addPathPatterns("/stopwatch", "/interceptor/**")
                .excludePathPatterns("/css/**", "/images/**", "/js/**", "/error");

    }
}
