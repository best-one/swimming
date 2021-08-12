package org.swimming.labs.rabbitmq.pubsub;


import lombok.Builder;
import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Data
@Builder
public class RabbitMQContainerFactory implements FactoryBean<SimpleMessageListenerContainer> {

    private List<RabbitCustomizerInfo> rabbitCustomizerInfos;

    private Boolean autoDeleted;
    private Boolean durable;
    private Boolean autoAck;

    private ConnectionFactory connectionFactory;
    private RabbitAdmin rabbitAdmin;
    private Integer concurrentNum;


    // 消费方
    private IMqConsumer consumer;




    private void check() {
        if (rabbitAdmin == null || connectionFactory == null) {
            throw new IllegalArgumentException("rabbitAdmin and connectionFactory should not be null!");
        }
    }


    @Override
    public SimpleMessageListenerContainer getObject() throws Exception {
        check();

        List<Queue> list = new ArrayList<>();
        rabbitCustomizerInfos.stream().forEach(
                rabbitCustomizerInfo -> {
//                    String queueName=rabbitCustomizerInfo.g;
//                    String exchangeName="";
//                    String routeKey="";
//                    // alarmChannel.exchangeDeclare(exchange, "topic", false, true, null);
//                    //翻译版本
//                    Exchange topicExchange = new TopicExchange(exchangeName,false,true,null);
//                    // alarmChannel.queueDeclare(queue, false, false, true, null);
//                    Queue queue = new Queue(queueName, false, false, true, null);
//                    BindingBuilder.bind(queue).to((DirectExchange) exchange).with(routeKey);
                    Exchange exchange = rabbitCustomizerInfo.getExchange();
                    Queue queue = rabbitCustomizerInfo.getQueue();
                    Binding binding = rabbitCustomizerInfo.getBinding();

                    rabbitAdmin.declareQueue(queue);
                    rabbitAdmin.declareExchange(exchange);
                    rabbitAdmin.declareBinding(binding);
                    list.add(queue);
                }
        );


        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setAmqpAdmin(rabbitAdmin);
//        container.setRabbitAdmin(rabbitAdmin);
        container.setConnectionFactory(connectionFactory);
//        container.setQueues(queue);
        container.setQueues(list.toArray(new Queue[]{}));
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

