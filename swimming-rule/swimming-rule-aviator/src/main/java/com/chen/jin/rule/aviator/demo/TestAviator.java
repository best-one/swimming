package com.chen.jin.rule.aviator.demo;

import java.util.HashMap;
import java.util.Map;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestAviator {

	public static Object test(String rule, Map<String, Object> data, boolean cache) {
		Object obj = null;
		try {
		obj = AviatorEvaluator.execute(rule, data, true);
		}catch (Exception e) {
			log.error("rule process error!!!",e);
		}
		return obj;
	}
	
	public static boolean isTrue(Object o) {
		Boolean flag = false;
		flag = (Boolean) o;
		return  flag!=null&&flag;
	}

	public static void main(String[] args) {
//		susan();
//		System.out.println(Integer.parseInt("35.9"));
		int a=34;
		double b = 56.7;
		System.out.println(a>b);
	}
	
	public static void susan() {
		String rule = "(lastValue<=50) && (value > 50)";
		Map<String,Object> map = new HashMap<>();
		map.put("value", 26.5);
		map.put("lastValue", 34);
		Expression compiledExp = AviatorEvaluator.compile(rule,true); 
		Boolean result = (Boolean) compiledExp.execute(map);
		log.info("final result::{}",result);
	}
	
	public static void me() {
        String expression = "a-(b-c)>100"; 
        // 编译表达式 
        Expression compiledExp = AviatorEvaluator.compile(expression); 
 
        Map<String, Object> env = new HashMap<String, Object>(); 
        env.put("a", 100.3); 
        env.put("b", 45); 
        env.put("c", -199.100); 
 
        // 执行表达式 
        Boolean result = (Boolean) compiledExp.execute(env); 
        System.out.println(result); 
	}
	
	//validate device point status failure : 
	//For input string: "26.5" , 
	//alarmRule :AlarmRule{type='cross.top', group='cross', name='越上限',
	//rule='lastValue<=50 && value > 50', message=''环境温度过高，请降低室内温度!'',
	//level=0, phone='null', sendType='user', targets='usr_9b9a2866-b431-4a66-a129-c5107e4b6f30',
	//valueList=[50]}
	public static void companyError() {
		String rule = "lastValue<=50.0 && value > 50.0";
		Map<String,Object> map = new HashMap<>();
		map.put("value", "26.5");
		map.put("lastValue", "34");
		Object o = test(rule,map,true);
		boolean flag = isTrue(o);
		log.info("final result::{}",flag);
	}
	
}
