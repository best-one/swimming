package com.jim.chen.data.excel.demo;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;

import lombok.Data;

@Data
public class Demo {
	
	private String dev;
	private String name;
	private String pointType="general";
	private String valueType="TWO_BYTE_INT_UNSIGNED";
	
	private int slaveId=2;
	private int offset=6;
	private String modbusType="HoldingRegister";
	private String description;
	private List<String> $valueTypeList=Arrays.asList("int","");
	private List<Integer> $control=Arrays.asList(0);
	
	public List<String> get$valueTypeList() {
		return $valueTypeList;
	}
	public void set$valueTypeList(List<String> $valueTypeList) {
		this.$valueTypeList = $valueTypeList;
	}
	public List<Integer> get$control() {
		return $control;
	}
	public void set$control(List<Integer> $control) {
		this.$control = $control;
	}

	public static void main(String[] args) {
		System.out.println(JSON.toJSONString(new Demo()));
	}
}
