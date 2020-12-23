package com.gg.driver.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
public class TestController {

	@GetMapping("/q")
	public TestReq ge(@RequestBody TestReq params){

		return params;
	}
	
	@Data
	public static class TestReq {
		private String test="test";
		private String name="run";
		private String ok="333";
	}

}
