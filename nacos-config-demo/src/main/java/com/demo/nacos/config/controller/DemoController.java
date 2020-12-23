package com.demo.nacos.config.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//1、这个注解是必须的，他会通知spring获取nacos的数据以及及时刷新
@RefreshScope
public class DemoController {
    //2、获取一些naocs配置的数据
	@Value("${my.sex}")
	private String sex;
	@Value("${my.age}")
	private String age;
	@Value("${my.test}")
	private String test;
	
	@GetMapping("/t")
	public Map<String,String> re(){
		Map<String,String> map = new HashMap<>();
		map.put("sex", sex);
		map.put("age", age);
		
		map.put("test", test);
		return map;
	}
	
}