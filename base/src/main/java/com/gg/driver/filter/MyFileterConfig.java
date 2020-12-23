package com.gg.driver.filter;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chen.demo.filter.ParamsFilter;

@Configuration
public class MyFileterConfig {

    @Autowired
    private ParamsFilter paramsFilter;

    @Bean
    public FilterRegistrationBean<Filter> registerAuthFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(paramsFilter);
        registration.addUrlPatterns("/*");
        registration.setName("record_prams_log");
        registration.setOrder(1);  //值越小，Filter越靠前。
        return registration;
    }
}
