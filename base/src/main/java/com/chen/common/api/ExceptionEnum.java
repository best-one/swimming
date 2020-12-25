package com.chen.common.api;

public enum ExceptionEnum {
	SUCCESS(0, "awesome, you are a Great programer!!")
	, ERROR(1, "you see see,errors in everywhere!!!")
	;

	private Integer code;
	private String message;

	ExceptionEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
