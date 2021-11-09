package org.swimming.labs.rabbitmq.jk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class TestService {
    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private RabbitAdmin rabbitAdmin;
    @Autowired
    private CustomizeDynamicConsumerContainer customizeDynamicConsumerContainer;
    @Autowired
    private MqConfig.AmqpProducer amqpProducer;


    public void dynamicCreateConsumer(){
        Map<String, DynamicConsumer> allQueue2ContainerMap = customizeDynamicConsumerContainer.customizeDynamicConsumerContainer;
        DynamicConsumer consumer = null;
        try {
            //创建消费者
            consumer = ConsumerGenerate
                    .genConsumer(connectionFactory, rabbitAdmin,"test001-1", "test001", "routingKey001"
                            , false, true, true);
        } catch (Exception e) {
            log.error("系统异常",e);
        }
        allQueue2ContainerMap.put("test001", consumer);
        //启动消费者
        consumer.start();
        //发送消息到交换机
        amqpProducer.publishMsg("test001-1", "routingKey001", "Hello MQ!");
    }

    /**
     * 暂停消费者
     */
    public void stop(){
        Map<String, DynamicConsumer> allQueue2ContainerMap = customizeDynamicConsumerContainer.customizeDynamicConsumerContainer;
        DynamicConsumer dynamicConsumer = allQueue2ContainerMap.get("test001");
        dynamicConsumer.stop();
    }


}