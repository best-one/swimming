package org.swimming.labs.message.redis.listener;

import org.springframework.data.redis.connection.stream.Record;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

public class MyRedisStreamListener implements StreamMessageListenerContainer<String, Record<String,String>>{

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Subscription register(StreamReadRequest<String> streamRequest,
			StreamListener<String, Record<String, String>> listener) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Subscription subscription) {
		// TODO Auto-generated method stub
		
	}

}
