package com.chen.demo.json.fastjson;


import lombok.Data;

@Data
public class DemoFastjson {

	public interface Names{}
	
//	@FastJsonFilter(clazz = Names.class)
	private String name;
	private String like;
	private Integer age;
	private Boolean hooby;
}
