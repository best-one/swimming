package com.mc.rabbitmq;

import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * 自定义消息接收类
 * @author lc
 */
@Component
public class Receive {

    public void handleMessage(String msg){
        System.err.println("handleMessage: "+ msg);
    }

    public void process(String msg){
        System.err.println("Process: "+ msg);
    }

    public void getM(String msg){
        System.err.println("getM: "+ msg);
    }
    public void getD(String msg){
        System.err.println("getD: "+ msg);
    }
}
