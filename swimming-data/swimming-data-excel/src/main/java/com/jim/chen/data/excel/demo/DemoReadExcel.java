package com.jim.chen.data.excel.demo;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;

public class DemoReadExcel {

	public static void main(String[] args) {
        String fileName = "D:\\工作日志\\中移动\\ccc.xlsx";
        DemoDataListener datal = new  DemoDataListener();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, datal).sheet().doRead();
        
        List<DemoData> list =  datal.getList();
        
        List<Demo> ret = new ArrayList<>(64);
        int i=0;
        for(DemoData data_temp: list) {
        	Demo now = new Demo();
        	now.setDev(data_temp.getDeviceCode());
        	now.setName(data_temp.getPointName());
        	now.setDescription(data_temp.getDesc());
        	now.setOffset(i);
        	ret.add(now);
        	i++;
        }
        System.out.println(JSON.toJSONString(ret));
        
	}
}
