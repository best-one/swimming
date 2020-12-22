package com.chen.demo.filter;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Filter在spring 中的配置
 * @ClassName:  MyFilterConfig   
 * @Description:Filter在spring 中的配置
 * @author: jim
 * @date:   2020年12月22日 上午10:16:49      
 * @Copyright:
 */
@Configuration
public class MyFilterConfig {


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
