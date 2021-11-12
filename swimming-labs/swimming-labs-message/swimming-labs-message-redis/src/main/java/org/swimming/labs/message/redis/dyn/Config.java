package org.swimming.labs.message.redis.dyn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import lombok.extern.slf4j.Slf4j;

/**
 * 定义redis消息监听容器，用于添加或者移除监听在一个redis
 * @author jim
 *
 */
@Slf4j
@Configuration
public class Config {

	

//  @Autowired
//  RedisMessageListenerContainer redisMessageListenerContainer;
  /**
   * 定义消息监听者容器
   * @param connectionFactory 连接工厂
   * @return RedisMessageListenerContainer
   */
  @Bean
  public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
//      log.info("RedisConnectionFactory::{}",connectionFactory.getClass().getCanonicalName());
      RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
      listenerContainer.setConnectionFactory(connectionFactory);
//      listenerContainer.addMessageListener(listenerAdapter, new PatternTopic("queue_yubaoshan"));
      return listenerContainer;
  }
}
