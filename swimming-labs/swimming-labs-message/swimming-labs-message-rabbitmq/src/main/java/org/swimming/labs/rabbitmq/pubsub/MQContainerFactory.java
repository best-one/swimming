package org.swimming.labs.rabbitmq.pubsub;


import lombok.Builder;
import lombok.Data;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.FactoryBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Data
@Builder
public class MQContainerFactory implements FactoryBean<SimpleMessageListenerContainer> {
    private ExchangeType exchangeType;

    private String directExchange;
    private String topicExchange;
    private String fanoutExchange;

    private String queue;
    private List<String> queues;
    private String routingKey;
    private List<String> routingKeys;


    private Boolean autoDeleted;
    private Boolean durable;
    private Boolean autoAck;

    private ConnectionFactory connectionFactory;
    private RabbitAdmin rabbitAdmin;
    private Integer concurrentNum;


    // 消费方
    private IMqConsumer consumer;


    private Exchange buildExchange() {
        if (directExchange != null) {
            exchangeType = ExchangeType.DIRECT;
            return new DirectExchange(directExchange);
        } else if (topicExchange != null) {
            exchangeType = ExchangeType.TOPIC;
            return new TopicExchange(topicExchange);
        } else if (fanoutExchange != null) {
            exchangeType = ExchangeType.FANOUT;
            return new FanoutExchange(fanoutExchange);
        } else {
            if (StringUtils.isEmpty(routingKey)) {
                throw new IllegalArgumentException("defaultExchange's routingKey should not be null!");
            }
            exchangeType = ExchangeType.DEFAULT;
            return new DirectExchange("");
        }
    }


    private Queue buildQueue() {
        if (StringUtils.isEmpty(queue)) {
            throw new IllegalArgumentException("queue name should not be null!");
        }

        return new Queue(queue, durable == null ? false : durable, false, autoDeleted == null ? true : autoDeleted);
    }


    private Binding bind(Queue queue, Exchange exchange) {
        return exchangeType.binding(queue, exchange, routingKey);
    }


    private void check() {
        if (rabbitAdmin == null || connectionFactory == null) {
            throw new IllegalArgumentException("rabbitAdmin and connectionFactory should not be null!");
        }
    }


    @Override
    public SimpleMessageListenerContainer getObject() throws Exception {
        check();
        List<String> queueNames = Arrays.asList("cc","bb");

        List<Queue> list = new ArrayList<>();
        Exchange exchange = buildExchange();
        queueNames.stream().forEach(
                queueName -> {
                    if (StringUtils.isBlank(queue)) {
                        return;
                    }
                    Queue queue = new Queue(queueName, durable == null ? false : durable, false, autoDeleted == null ? true : autoDeleted);
                    //Binding binding = bind(queue, exchange);
                    String routingKey = "#."+queueName+".#";
                    Binding  binding =  exchangeType.binding(queue, exchange, routingKey);
                    list.add(queue);

                    rabbitAdmin.declareQueue(queue);
                    rabbitAdmin.declareExchange(exchange);
                    rabbitAdmin.declareBinding(binding);
                }
        );




//        //真实情况是，一个通道多个队列
//        Queue queue = buildQueue();
//        Exchange exchange = buildExchange();
//        Binding binding = bind(queue, exchange);
//
//        rabbitAdmin.declareQueue(queue);
//        rabbitAdmin.declareExchange(exchange);
//        rabbitAdmin.declareBinding(binding);

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setAmqpAdmin(rabbitAdmin);
//        container.setRabbitAdmin(rabbitAdmin);
        container.setConnectionFactory(connectionFactory);
//        container.setQueues(queue);
        container.setQueues(list.toArray(new  Queue[]{}));
        container.setPrefetchCount(20);
        container.setConcurrentConsumers(concurrentNum == null ? 1 : concurrentNum);
        container.setAcknowledgeMode(autoAck ? AcknowledgeMode.AUTO : AcknowledgeMode.MANUAL);


        if (consumer != null) {
            container.setMessageListener(consumer);
        }
        return container;
    }

    @Override
    public Class<?> getObjectType() {
        return SimpleMessageListenerContainer.class;
    }
}

