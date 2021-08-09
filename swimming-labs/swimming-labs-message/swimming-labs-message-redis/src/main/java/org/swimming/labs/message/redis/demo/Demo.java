package org.swimming.labs.message.redis.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.swimming.labs.message.redis.listener.MyRedisListener;

@RestController
public class Demo {

	@Autowired
	RedisMessageListenerContainer redisContainer;
	
	@PostMapping("/message")
	public void init(List<String> topic) {
		
		List<Topic> topicList = topic.stream().map(ChannelTopic::new).collect(Collectors.toList());
		
		redisContainer.addMessageListener(new MyRedisListener(), topicList);
		

	}
}
