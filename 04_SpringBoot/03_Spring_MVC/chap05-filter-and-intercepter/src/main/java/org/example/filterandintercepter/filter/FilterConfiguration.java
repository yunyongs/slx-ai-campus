package org.example.filterandintercepter.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    // Filter는 Servlet 기술이므로 Servlet Container에 등록해야함.
    // FilterRegistrationBean
    // - Spring Context에 Bean으로 Filter를 등록 할 수 있게 해주는 클래스
    //   - 어떤 Filter 객체를 등록할지
    //   - 어떤 URL에 적용할지
    //   - 여러 Filter 중 몇번째로 실행할지
    @Bean
    public FilterRegistrationBean<StopWatchFilter> stopWatchFilterFilterRegistration() {

        FilterRegistrationBean<StopWatchFilter> registration = new FilterRegistrationBean<>();

        registration.setFilter(new StopWatchFilter()); // Filter 객체를 Servlet Container에 등록

        // 해당 패턴의 URL로 요청이 왔을때, 이 필터가 동작하겠다.
        registration.addUrlPatterns("/stopwatch", "/filter/*");

        registration.setOrder(1); // 필터의 적용 순서를 명시 (숫자가 작을수록 먼저 실행됨)
        registration.setName("stopWatchFilter");

        return registration;
    }
}
