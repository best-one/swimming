package org.swimming.labs.template.velocity.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.*;

@Slf4j
public class GenUtils {

    public static void main(String[] args) {
        test();
//        ab();

    }

    public static void ab(){
        String a="Oma0ZvHF0yA6gWQ2NBmPOxpiyk3qUOO0vajveYzGYXGE1axVYIuR2CTCfzR0AjTWdarQcH7uHLnQD4FxThx5Vw==";
        String b = "Pt6UMT66ZNR3B1fmDdf3QEbIp03RppgyPM+0VouIxafBXz6T9faOzHnMbd0vXf5j5PwaPsJnY6xgj6jhQURp0g==";
        System.out.println(a+"::length::"+a.length());
        System.out.println(b+"::length::"+b.length());
    }

    public static void test(){
        String template = "template/tea.vm";

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//        prop.put("resource.loader", "globbing,string");
//        prop.put("globbing.resource.loader.class ", "StructuredGlobbingResourceLoader");
//        prop.put("string.resource.loader.class ", " org.apache.velocity.runtime.resource.loader.StringResourceLoader");
        Velocity.init(prop);

        List<String> ttt = Arrays.asList("张三","李四","王武","john","tom","papa");
        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("pada" ,ttt);

        VelocityContext context = new VelocityContext(map);

        //渲染模板
        StringWriter sw = new StringWriter();
        Template tpl = Velocity.getTemplate(template, "UTF-8");
        tpl.merge(context, sw);

        String result = sw.toString();
        log.info("result::{}",result);
    }
}
