package org.swimming.labs.rabbitmq.pubsub;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化rabbit配置
 */
@Configuration
public class DynSpringConfig {


    @Value("${spring.rabbitmq.host}")
    private String rabbitmqHost;

    @Value("${spring.rabbitmq.port}")
    private int rabbitmqPort;

    @Value("${spring.rabbitmq.username}")
    private String rabbitmqUsername;

    @Value("${spring.rabbitmq.password}")
    private String rabbitmqPassword;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(rabbitmqHost);
        factory.setPort(rabbitmqPort);
        factory.setUsername(rabbitmqUsername);
        factory.setPassword(rabbitmqPassword);

        return factory;
    }

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


