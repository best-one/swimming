package com.mc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitMQConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;


    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());

        //消息是否成功发送到Exchange
        rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> {
            String msgId = correlationData.getId();
            if(ack){
                log.info("correlationData:{}",correlationData.toString());
                log.info("消息成功发送到 Exchange");

            }else{
                log.info("消息发送到Exchange失败，{},cause:{}",correlationData,cause);
            }
        }));
        // 触发setReturnCallback回调必须设置mandatory=true,否则Exchange没有找到Queue就会丢弃消息，
        // 而不会触发回调
        rabbitTemplate.setMandatory(true);
        //消息是否从Exchange路由到Queue,注：这是个失败回调，只有消息从Exchange路由到Queue失败，才会
        // 回調这个方法
        rabbitTemplate.setReturnCallback((Message message, int replyCode, String replyText, String exchange, String routingKey) ->{
            String msgId = String.valueOf(message.getMessageProperties().getHeaders().get("spring_returned_message_correlation"));
            log.info("消息从Exchange路由到Queue失败，exchagne:{},routingKey:{},replyCode:{},replyText:{},message:{}",exchange,routingKey,replyCode,replyText,message);
        });
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
}
