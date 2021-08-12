package org.swimming.labs.rabbitmq.pubsub;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import java.nio.charset.StandardCharsets;

/**
 * Created by yihui in 18:51 18/6/4.
 */
@Slf4j
public class DynamicConsumer {
    private SimpleMessageListenerContainer container;

    /**
     *
     * @param fac
     * @param name
     * @param messageType
     * @throws Exception
     */
    public DynamicConsumer(MQContainerFactory fac, String name,String messageType) throws Exception {
        SimpleMessageListenerContainer container = fac.getObject();
        container.setMessageListener(new AbsMQConsumer() {
            @Override
            public boolean process(Message message, Channel channel) {
                //TODO 填充逻辑
                String messageStr = new String(message.getBody(), StandardCharsets.UTF_8);
                log.info("收到的消息:::{}", messageStr);


                return true;
            }
        });
        this.container = container;
    }

    public void start() {
        container.start();
    }

    public void stop() {
        container.stop();
    }

    public void shutdown() {
        container.shutdown();
    }
}