package com.mc;

import com.mc.rabbitmq.Receive;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 动态订阅
 */
@Component
@Order(1)
public class MyApplicationRunner implements CommandLineRunner {
    private final SimpleMessageListenerContainer container;
    private final Receive receive;

    public MyApplicationRunner(SimpleMessageListenerContainer container,Receive receive) {
        this.container = container;
        this.receive = receive;
    }

    @Override
    public void run(String... args) throws Exception {
//        System.err.println("CommandLineRunner");
//        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(receive,new Jackson2JsonMessageConverter());
//        messageListenerAdapter.addQueueOrTagToMethodName("QueueName","handleMessage");
//        messageListenerAdapter.addQueueOrTagToMethodName("lcTest","process");
//        messageListenerAdapter.addQueueOrTagToMethodName("fjTest","getM");
//        messageListenerAdapter.addQueueOrTagToMethodName("dpTest","getD");
//        String[] strings = {"QueueName","lcTest","fjTest","dpTest"};
//        container.addQueueNames(strings);
//        container.setMessageListener(messageListenerAdapter);
    }
}
