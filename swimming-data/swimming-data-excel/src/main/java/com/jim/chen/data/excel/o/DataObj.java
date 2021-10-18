package com.jim.chen.data.excel.o;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

@Data
public class DataObj {

	@ExcelProperty(index = 1)
	private String company;
	
	@ExcelProperty(index = 2)
	private String userName;
	
	@ExcelProperty(index = 3)
	private String password;
	
	
}
