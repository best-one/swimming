package com.chen.jin.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * 看下面配置
 * https://mapper.mybatis.io/docs/1.getting-started.html#_1-4-1-%E5%AE%9E%E4%BD%93%E7%B1%BB%E9%85%8D%E7%BD%AE
 */
@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestMapper testMapper;

    public void test(){

    }
}
