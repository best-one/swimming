package com.mc.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * 固定订阅某个Queue,当同时订阅时，因为不是广播，所以会随机消费
 * @author lc
 */
//@Component
//@RabbitListener(queues = "QueueName")
public class RabbitReceive {

//    @RabbitHandler
//    public void processMessage(byte[] content){
//        System.err.println(new String(content, Charset.defaultCharset()));
//    }
}
