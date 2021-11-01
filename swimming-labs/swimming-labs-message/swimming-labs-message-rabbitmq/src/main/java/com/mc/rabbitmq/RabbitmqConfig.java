package com.mc.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 初始配置
 * @author lc
 */
@Component
public class RabbitmqConfig {

    @Value("${params.Rabbitmq.ExchangeName}")
    private String exchangeName;

    @Value("${params.Rabbitmq.QueueName}")
    private String queueName;

    private AmqpAdmin amqpAdmin;
    @Autowired
    public void setAmqpAdmin(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    /**
     *  项目启动即能创建的Exchange
     *  可以创建各种类型的Exchange，父类都是 AbstractExchange
     *  这里举例Topic类型
     *  如果需要创建多个同类型可以用@Bean(name="beanName")，引用时用@Qualifier("beanName" )
     */
    @Bean
    public TopicExchange exchange(){
        TopicExchange dataExchange = new TopicExchange(exchangeName,true,false);
        amqpAdmin.declareExchange(dataExchange);
        return dataExchange;
    }

    /**
     * 项目创建就生成的Queue
     * @return
     */
    @Bean
    public Queue queue(){
        Queue queue = new Queue(queueName,true,false,false);
        amqpAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue test(){
        Queue queue = new Queue("lcTest",true,false,false);
        amqpAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue test2(){
        Queue queue = new Queue("fjTest",true,false,false);
        amqpAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue test3(){
        Queue queue = new Queue("dpTest",true,false,false);
        amqpAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Binding queueB(){
        Binding binding = BindingBuilder.bind(new Queue(queueName)).to(new TopicExchange(exchangeName)).with("test.#");
        amqpAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public Binding queueF(){
        Binding binding = BindingBuilder.bind(new Queue("fjTest")).to(new TopicExchange(exchangeName)).with("test.#");
        amqpAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public Binding queueL(){
        Binding binding = BindingBuilder.bind(new Queue("lcTest")).to(new TopicExchange(exchangeName)).with("test.#");
        amqpAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public Binding queueD(){
        Binding binding = BindingBuilder.bind(new Queue("dpTest")).to(new TopicExchange(exchangeName)).with("test.#");
        amqpAdmin.declareBinding(binding);
        return binding;
    }
}
