package com.chen.demo.json.jackson;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class DemoJackson {

	public interface Aview{}
	
	public interface Bview{}
	
	@JsonView(value = {Aview.class,Bview.class} )
	private String name;
	@JsonView(value = {Aview.class} )
	private String sex;
	@JsonView(Bview.class)
	private String like;
	@JsonView(Bview.class)
	private String birhtDay;
}
