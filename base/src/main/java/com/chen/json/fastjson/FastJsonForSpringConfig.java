package com.chen.json.fastjson;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * fastjson的配置在spring boot mvc中
 * 下面的扫描包动作是为了使用fastjsonview,参考例子可以看demo里面的fastjsonDemo
 */
@Configuration
//下面这个扫描是为了用fastjsonview来
@ComponentScan(basePackages = "com.alibaba.fastjson.support.spring")
public class FastJsonForSpringConfig {

//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		//1、定义一个convert转换消息的对象
//		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//		 //2、添加fastjson的配置信息
//		FastJsonConfig fastJsonConfig = new FastJsonConfig();
//		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
//				SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteNullListAsEmpty,
//				SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
//		List<MediaType> fastMediaTypes = new ArrayList<>();
////		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//		fastMediaTypes.add(MediaType.APPLICATION_JSON);
//		//3、在convert中添加配置信息
//		fastConverter.setSupportedMediaTypes(fastMediaTypes);
//		fastConverter.setDefaultCharset(StandardCharsets.UTF_8);
//		fastConverter.setFastJsonConfig(fastJsonConfig);
//		//补充一个string转换器
//		StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//		converters.add(0, converter);
//		//4、将convert添加到converters中
//		converters.add(0, fastConverter);
//	}

	@SuppressWarnings("deprecation")
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		//1、定义一个convert转换消息的对象
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		//2、添加fastjson的配置信息
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
				SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
		//3、在convert中添加配置信息
		fastConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8,
				MediaType.APPLICATION_OCTET_STREAM));
		fastConverter.setDefaultCharset(StandardCharsets.UTF_8);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter = fastConverter;
		//4、将convert添加到converters中
		return new HttpMessageConverters(converter);
	}
}
