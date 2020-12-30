package com.chen.json.fastjson;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
 
/**
 * 添加fastjson的转换
 */
//@Configuration
public class MyFastjsonConverter {
	//不要这个日期设置，为了使用注解更方便定制格式化
//    final static String _DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
 
    @Bean
    public HttpMessageConverters customConverters() {
        // 定义一个转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 添加fastjson的配置信息 比如 ：是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 这里就是核心代码了，WriteMapNullValue把空的值的key也返回
        fastJsonConfig.setSerializerFeatures(
                //保留map空的字段
                SerializerFeature.WriteMapNullValue,
                // 将String类型的NULL转化为""
                SerializerFeature.WriteNullStringAsEmpty,
                // 将Number类型的NULL转化为0
                SerializerFeature.WriteNullNumberAsZero,
                // 将List类型的NULL转成[]
                SerializerFeature.WriteNullListAsEmpty,
                // 将Boolean类型的NULL转化为false
                SerializerFeature.WriteNullBooleanAsFalse,
                // 避免循环引用
                SerializerFeature.DisableCircularReferenceDetect);
 
        //如果时间类型值为null，则返回空串
        ValueFilter dateFilter = (Object var1, String var2, Object var3) -> {
            try {
                if (var3 == null && var1 != null && Date.class.isAssignableFrom(var1.getClass().getDeclaredField(var2).getType())) {
                    return "";
                }
            } catch (Exception e) {
            }
            return var3;
        };
        fastJsonConfig.setSerializeFilters(dateFilter);
 
//        fastJsonConfig.setDateFormat(_DATE_FORMAT);
 
        List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
 
        // 处理中文乱码问题
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        // 在转换器中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
 
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setDefaultCharset(Charset.forName("UTF-8"));
        stringConverter.setSupportedMediaTypes(fastMediaTypes);
 
        // 将转换器添加到converters中
        return new HttpMessageConverters(stringConverter, fastConverter);
    }
}