package com.bear.mvcassignment.problem06;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<LogFilter> logFilterFilterRegistrationBean() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        registrationBean.setName("LogFilter");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<BadWordFilter> badWordFilterFilterRegistrationBean() {
        FilterRegistrationBean<BadWordFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new BadWordFilter());
        registrationBean.addUrlPatterns("/parameters/search");
        registrationBean.setOrder(2);
        registrationBean.setName("BadWordFilter");

        return registrationBean;
    }
}
