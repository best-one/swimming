package com.mc;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author lc
 * @date  2018/5/7
 */
@EnableAsync
@SpringBootApplication
public class Application {

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory){
        return new SimpleMessageListenerContainer(connectionFactory);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.err.println("runAfter");
    }
}
