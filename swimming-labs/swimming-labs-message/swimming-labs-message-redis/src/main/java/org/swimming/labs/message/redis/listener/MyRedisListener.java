package org.swimming.labs.message.redis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MyRedisListener implements MessageListener{

	private String customerId;
//	private Map<String,Map<session,session>>
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
        //消息获取到后的处理消息
        String channel = new String(message.getChannel());
        String body = new String(message.getBody());
        log.debug("channel:{},body:{}",channel,body);
		
	}

}
