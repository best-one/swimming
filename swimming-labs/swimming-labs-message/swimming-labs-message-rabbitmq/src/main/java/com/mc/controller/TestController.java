package com.mc.controller;

import com.mc.rabbitmq.RabbitSendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "Test")
public class TestController {
    private RabbitSendUtil rabbitSendUtil;
    @Autowired
    public void setRabbitSendUtil(RabbitSendUtil rabbitSendUtil) {
        this.rabbitSendUtil = rabbitSendUtil;
    }

    @GetMapping(value = "test")
    public String test(){
        return "success";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/send/lcTest/{message}")
    public void sendTest1(@PathVariable String message){
        rabbitSendUtil.sendToQueue("lcTest",message);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/send/QueueName/{message}")
    public void sendTest2(@PathVariable String message){
        rabbitSendUtil.sendToQueue("QueueName",message);
    }


}
