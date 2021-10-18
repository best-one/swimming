package com.jim.chen.data.excel.o;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadGangHang {

	public static void main(String[] args) throws FileNotFoundException {
		
		 String fileName = "c:/a.xlsx";
		 
		 DataObjListener listener = new DataObjListener();
		 
//		 EasyExcel.read(new File(fileName), listener).sheet().doRead();
//		 EasyExcel.read(fileName, DataObj.class, listener).sheet().doRead();
		 
		 EasyExcel.read(new FileInputStream(new File(fileName)), DataObj.class, listener).sheet().doRead();
		 
		 List<DataObj> list =  listener.getList();
		 log.info("ff/:::{}",list.size());
		// list.forEach(s->{log.info("result::{}",s);});
		 log.info(JSON.toJSONString(list));
	}
	
}
