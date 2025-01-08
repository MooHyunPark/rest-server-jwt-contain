package com.metacoding.restserver._core.config;

import com.metacoding.restserver._core.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter());
        bean.addUrlPatterns("/*"); // * 하나만 써야됨. 
        bean.setOrder(0); // 낮은 번호부터 실행됨.
        return bean;
    }
}