package com.mc;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(99)
public class SendRunner implements CommandLineRunner {

    private final RabbitTemplate RabbitTemplate;

    public SendRunner(RabbitTemplate amqpTemplate) {
        this.RabbitTemplate = amqpTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
//        User user = new User();
//        user.setId("1");
//        user.setName("2");
        for (int i = 0; i < 10000000; i++) {
            RabbitTemplate.convertAndSend("ExchangeName","test.s","kkkk");
            RabbitTemplate.convertAndSend(DirectExchange.DEFAULT.getName(),"queueName","message");
        }

    }
}
