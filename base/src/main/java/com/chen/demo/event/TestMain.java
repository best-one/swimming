package com.chen.demo.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 模拟
 * @ClassName:  TestMain   
 * @Description:TODO(描述这个类的作用)   
 * @author: jim
 * @date:   2020年12月22日 上午11:48:50      
 * @Copyright:
 */
@Configuration
@ComponentScan("com.chen.demo.event")
public class TestMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicaiton = new AnnotationConfigApplicationContext(TestMain.class);
		UserService userService = applicaiton.getBean(UserService.class);
		userService.register("hah");
		applicaiton.close();
	}
}
