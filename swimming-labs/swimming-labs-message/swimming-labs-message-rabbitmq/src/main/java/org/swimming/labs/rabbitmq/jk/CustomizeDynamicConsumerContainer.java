package org.swimming.labs.rabbitmq.jk;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CustomizeDynamicConsumerContainer {

    /**
     * 用于存放全局消费者
     */
    public final Map<String, DynamicConsumer> customizeDynamicConsumerContainer = new
            ConcurrentHashMap<>();
}
