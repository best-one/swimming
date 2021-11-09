package org.swimming.labs.rabbitmq.jimsub;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitSub {

	private static final String INFERRED_FOO_QUEUE = "sample.inferred.foo";

	private static final String INFERRED_BAR_QUEUE = "sample.inferred.bar";

	private static final String RECEIVE_AND_CONVERT_QUEUE = "sample.receive.and.convert";

	private static final String MAPPED_QUEUE = "sample.mapped";
	
	@Autowired
	private AmqpAdmin rabbitAdmin;
	

	@Bean
	public SimpleMessageListenerContainer legacyPojoListener(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueueNames(MAPPED_QUEUE);

		MessageListenerAdapter messageListener = new MessageListenerAdapter(new Object() {

			@SuppressWarnings("unused")
			public void handleMessage(Object object) {
				System.out.println("Got a " + object);
//				Application.this.latch.countDown();
			}

		});
		Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();

//		jsonConverter.setClassMapper(classMapper());
		messageListener.setMessageConverter(jsonConverter);
		container.setMessageListener(messageListener);
		container.start();
		return container;
	}
	
}
