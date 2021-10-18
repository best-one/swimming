package com.jim.chen.data.excel.o;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class DataObjListener  extends AnalysisEventListener<DataObj> {
   
	private List<DataObj> list = new ArrayList<DataObj>();
	
	@Override
	public void invoke(DataObj data, AnalysisContext context) {
		list.add(data);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		log.info("read all !!");
	}

}
