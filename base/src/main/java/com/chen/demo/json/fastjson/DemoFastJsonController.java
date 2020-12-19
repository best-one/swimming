package com.chen.demo.json.fastjson;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;

@RestController
@RequestMapping("/t")
public class DemoFastJsonController {

	/**
	 * 只显示name ，age这两个字段，其他属性屏蔽
	 * @Title: test   
	 * @Description: TODO(描述这个方法的作用)   
	 * @param: @return      
	 * @return: DemoFastjson      
	 * @throws
	 */
	@GetMapping("d")
	@FastJsonView(include = {
			@FastJsonFilter(clazz = DemoFastjson.class, props = { "name","age" })
	})
	public DemoFastjson test() {
		DemoFastjson demo = new DemoFastjson();
		demo.setAge(1);
		demo.setHooby(false);
		demo.setLike("v");
		demo.setName("汪公开");

		return demo;
	}
	/**
	 * 对于list集合的返回，fastjson视图定义了需要的字段展示，感觉不如jackson好用
	 * @Title: testList   
	 * @Description: TODO(描述这个方法的作用)   
	 * @param: 
	 * @return: List<DemoFastjson> 
	 * @throws
	 */
	@GetMapping("dd")
	@FastJsonView(include = {
			@FastJsonFilter(clazz = DemoFastjson.class, props = { "hooby","age","name" })
	})
	public List<DemoFastjson> testList() {
		List<DemoFastjson> list = new ArrayList<>();
		for(int i=0 ;i <10;i++) {
			DemoFastjson demo = new DemoFastjson();
			demo.setAge(i);
			demo.setHooby(i/2==0);
			demo.setLike("v"+i);
			demo.setName("汪公开"+i);
			list.add(demo);
		}
		return list;
	}
}