package com.chen.common.api;

import lombok.Data;

/**
 * api的入参格式
 * @ClassName:  ApiReq   
 * @Description:TODO(描述这个类的作用)   
 * @author: jim
 * @date:   2020年12月25日 下午6:27:58      
 * @Copyright:
 */
@Data
public class ApiReq {

	/**
	 * 参数加密结果
	 */
	private String sign;
	/**
	 * 时间串,用于校验入参的时间，会追加到data里面计算
	 */
	private Integer timestamp;
	private Object data;
	
	
	
}
