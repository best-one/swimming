package com.chen.demo.json.jackson;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/jacksonTest")
public class DemoJacksonController {

	@GetMapping("a")
	@JsonView(DemoJackson.Aview.class)
	public DemoJackson haha() {
		DemoJackson demoJack= new DemoJackson();
		demoJack.setBirhtDay("2012-12-12");
		demoJack.setLike("chi");
		demoJack.setName("wahaha");
		demoJack.setSex("tank");
		return demoJack;
	}
	
	@GetMapping("t")
    @JsonView(DemoJackson.Bview.class)
	public List<DemoJackson> hahaList() {
		List<DemoJackson> list = new ArrayList<>();
		for(int i=0;i<2;i++) {
			DemoJackson demoJack= new DemoJackson();
			demoJack.setBirhtDay("2012-12-12");
			demoJack.setLike("chi"+i);
			demoJack.setName("wahaha"+i);
			demoJack.setSex("tank"+i);
			list.add(demoJack);
		}
		return list;
	}
}
