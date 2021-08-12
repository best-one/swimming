package org.swimming.labs.rabbitmq.pubsub;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

/**
 * 消费者创建方式
 */
public class ConsumerGenerate {

    /**
     * 创建消费者
     *
     * @param connectionFactory
     * @param rabbitAdmin
     * @param exchangeName
     * @param queueName
     * @param routingKey
     * @param autoDelete
     * @param durable
     * @param autoAck
     * @param messageType 消息类型，用于websocket回传消息时候的标识
     * @return
     * @throws Exception
     */
    public static DynamicConsumer genConsumer(ConnectionFactory connectionFactory, RabbitAdmin rabbitAdmin,
                                              String exchangeName, String queueName, String routingKey, boolean autoDelete, boolean durable,
                                              boolean autoAck,String messageType) throws Exception {

        MQContainerFactory fac =
                MQContainerFactory.builder().directExchange(exchangeName).queue(queueName).autoDeleted(autoDelete)
                        .autoAck(autoAck).durable(durable).routingKey(routingKey).rabbitAdmin(rabbitAdmin)
                        .connectionFactory(connectionFactory).build();
        return new DynamicConsumer(fac, queueName,messageType);
    }

    /**
     *
     * @param fac  rabbit容器工厂
     * @param customerId 租户编号
     * @param messageType  消息类型
     * @return
     * @throws Exception
     */
    public static DynamicConsumer makeConsumer( MQContainerFactory fac,String customerId,String messageType) throws Exception {
        return new DynamicConsumer(fac, customerId,messageType);
    }

}

