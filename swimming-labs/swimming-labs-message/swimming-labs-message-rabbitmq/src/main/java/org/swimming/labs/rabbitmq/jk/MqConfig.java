package org.swimming.labs.rabbitmq.jk;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {


    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }


    @Bean
    public AmqpProducer amqpProducer(ConnectionFactory connectionFactory) {
        return new AmqpProducer(connectionFactory);
    }

    public class AmqpProducer {

        private AmqpTemplate amqpTemplate;

        public AmqpProducer(ConnectionFactory connectionFactory) {
            amqpTemplate = new RabbitTemplate(connectionFactory);
        }

        /**
         * 将消息发送到指定的交换器上
         *
         * @param exchange
         * @param msg
         */
        public void publishMsg(String exchange, String routingKey, Object msg) {
            amqpTemplate.convertAndSend(exchange, routingKey, msg);
        }
    }
}
