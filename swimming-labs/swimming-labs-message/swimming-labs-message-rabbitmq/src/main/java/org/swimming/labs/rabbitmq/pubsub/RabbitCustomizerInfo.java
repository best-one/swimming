package org.swimming.labs.rabbitmq.pubsub;

import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;

/**
 * 队列定制信息，用于队列容器的构建
 */
@Data
public class RabbitCustomizerInfo {
    /** exchange定义 */
    private Exchange exchange;
    /** queue定义 */
    private Queue queue;

//    private String routeKey;
    /** binding定义 */
    private Binding binding;


}

