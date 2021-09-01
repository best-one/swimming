package com.chen.data.redis.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RedisTestController {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	
	
	Map<String,String> map = new HashMap<>();
	List<String> list = new ArrayList<>();
	List<String> lostKey = new ArrayList<>();
	@GetMapping("/")
	public String redisTest() {
//		ListOperations listOperations =stringRedisTemplate.opsForList();
//		listOperations.getOperations().
		
		
		Set<String> stringKey = stringRedisTemplate.keys("*");
		log.info("keysize::{}",stringKey.size());
		
		for(String key: stringKey) {
			if(key.endsWith(",alarm")) {
				list.add(key);
			}else if(key.endsWith(",alarm_new")) {
				map.put(key, key);
			}else {
				
			}
		}
		for(String key : list) {
			if(!map.containsKey(key+"_new")) {
				lostKey.add(key);
				log.info("{}==》没有警告",key);
			}
		}
		Collections.sort(lostKey);
		log.info("lostKey::{}",JSON.toJSONString(lostKey));
		return "complete!!";
	}
	
	/**
	 * 获取这些key对应的信息
	 */
	@GetMapping("/d")
	public void testp() {
		for(String key : lostKey) {
			try {
				BoundHashOperations<String,String,String> bho = stringRedisTemplate.boundHashOps(key+"_new");
				Map<String,String> alarmData =bho.entries();
//				Map alarmData = stringRedisTemplate.boundHashOps(key).entries();
				log.info("key:{},result:[{}]",key,CollectionUtils.isEmpty(alarmData));
			}catch(Exception e) {
				log.error("[{}]对应结果出错",key,e);
			}
			 
		}
	}
	
	public static void main(String[] args) {
		String s = "1630314686282,1,E285";
		//1629480469649,1,E285 1629466515987,0,E285
		System.out.println(s.split(",").length);
	}
	
}
