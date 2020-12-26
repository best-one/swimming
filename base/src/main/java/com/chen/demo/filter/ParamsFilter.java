package com.chen.demo.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * 安心获取请求流跟响应流的Filter
 * ，为啥用filter，是因为有时候会有全局相应参数包装，就是ControlerAdvice注解的类实现ResponseBody
 * 在spring 里面，有这么四个过滤路线，从外到内
 * Filter :==> Interceptor :==> ControllerAdvice :==> aspect(学名AOP) :==> Controller
 * 
 * @ClassName:  ParamsFilter   
 * @Description:获取流里面的请求相应参数有  
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
		byte[] bytes = resp.getResponseData();
		String reqData = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8);
		String respData = IOUtils.toString(bytes, StandardCharsets.UTF_8.name());
		Map<String,String[]> map = req.getParameterMap();
		//3、有时候，输出的json有美化，需要输出消除空格
		reqData = reqData.replaceAll("\\s", "");
		log.debug("request  url ：{}",url);
		log.debug("普通 get url params：{}",JSON.toJSONString(map));
		log.debug("request json  data：{}",reqData);
		log.debug("response data：{}",respData);
		//4、靓点，不回写到流里面，这个流就永远是空流，然后请求就没响应
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
//		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		//点睛的地方，需要把数据重新回写
		log.info("length：{}"+resp.getBufferSize());
//		response.setContentLength(resp.getBufferSize());
		//5、这又是两个关注点，需要告诉浏览器等客户端这个相应的长度跟编码，不然可能出问题
		response.setContentLength(bytes.length);
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
//		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		//6、最后的倔强，流永远要关闭的，顺手的操作
		PrintWriter out = response.getWriter();
		out.write(respData);
		out.flush();
		out.close();
	}

}
