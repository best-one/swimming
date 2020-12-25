package com.chen.common.api;

@SuppressWarnings("serial")
public class ParamException extends RuntimeException {

	private Integer code;

	public ParamException(String message) {
		super(message);
	}
	
	public ParamException(Integer code, String message) {
		super(message);
		this.code = code;
	}
	
	public ParamException(ExceptionEnum exceptionEnum) {
		this(exceptionEnum.getCode(),exceptionEnum.getMessage());
	}
	
	public ParamException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParamException(Throwable cause) {
		super(cause);
	}

	protected ParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
