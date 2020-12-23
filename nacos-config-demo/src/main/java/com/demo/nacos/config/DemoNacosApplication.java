package com.demo.nacos.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//nacos启动配置
//配置dev环境的两种方式，一种是JVM启动参数，一种是
//-Dspring.profiles.active="dev"
//System.setProperty("spring.profiles.active", "dev");
//@Profile("dev") 这种只是设置spring里面对应的配置或者bean的对应环境
@SpringBootApplication
public class DemoNacosApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DemoNacosApplication.class, args);
	}
}
