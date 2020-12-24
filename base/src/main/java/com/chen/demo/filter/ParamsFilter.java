package com.chen.demo.filter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 安心获取请求流跟响应流的Filter
 * ，为啥用filter，是因为有时候会有全局相应参数包装，就是ControlerAdvice注解的类实现ResponseBody
 * 在spring 里面，有这么四个过滤路线，从外到内
 * Filter :==> incepter :==> controllerAdvice :==> aspect(学名AOP) :==> Controller
 * 
 * @ClassName:  ParamsFilter   
 * @Description:TODO(描述这个类的作用)   
 * @author: jim
 * @date:   2020年12月22日 上午10:09:49      
 * @Copyright:
 */
@Component
@Slf4j
public class ParamsFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//1、包装请求流跟相应流，不然后面的controller获取的都是空流，没有数据
		RequestWrapper req = new RequestWrapper((HttpServletRequest) request);
		ResponseWrapper resp = new ResponseWrapper((HttpServletResponse) response);
//		chain.doFilter(request, response);
		//2、用包装类替换掉一次流
		chain.doFilter(req, resp);
		//在上面这个方法前后可以计算一次请求的时间，这里不需要，我只需要记录请求相应的数据
		String url = req.getRequestURI();
		String reqData = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8);
		String respData = IOUtils.toString(resp.getResponseData(), StandardCharsets.UTF_8.name());
		//3、有时候，输出的json有美化，需要输出消除空格
		reqData = reqData.replaceAll("\\s", "");
		log.debug("request  url ：{}",url);
		log.debug("request  data：{}",reqData);
		log.debug("response data：{}",respData);
		//4、靓点，不回写到流里面，这个流就永远是空流，然后请求就没响应
		response.getOutputStream().write(respData.getBytes(StandardCharsets.UTF_8));
	}

}
