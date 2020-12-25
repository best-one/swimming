package com.chen.common.api;

import lombok.Data;

/**
 * api的相应结果
 * @ClassName:  ApiResp   
 * @Description:api的相应结果  
 * @author: jim
 * @date:   2020年12月25日 下午6:27:40      
 * @Copyright:
 */
@Data
public class ApiResp {
	//成功代码
	public static final int SUCCESS = 0;
	//失败代码
	public static final int FAIL = 1;
	//错误信息
	public static final String errorMsg = "you see see,errors in everywhere!!!";
	
	/**
	 * 接口相应码
	 */
	private Integer code;
	
	/**
	 * 错误消息提醒
	 */
	private String message;
	
	/**
	 * 时间戳
	 */
	private Integer timestamp;
	
	/**
	 * 成功时候的相应数据
	 */
	private Object data;
	
	private ApiResp() {
		
	}

	private ApiResp(String message) {
		this.code = FAIL;
		this.message = message;
	}

	/**
	 * 
	 * @Title: error   
	 * @Description: 用于对象构建，不建议写错误消息，当然你也可以写@see{}
	 * @return: ApiResp      
	 * @throws
	 */
	public static ApiResp serror() {
		return new ApiResp(errorMsg);
	}
	
	public static ApiResp serror(String message) {
		return new ApiResp(message);
	}

	public void error(String message) {
		this.code = FAIL;
		this.message = message;
		this.data=null;
	}

	public void error(int code, String message) {
		this.code = code;
		this.message = message;
		this.data = null;
	}

	public void succes(Object data) {
		this.code = SUCCESS;
		this.message = "";
		this.data = data;
	}
	
}
