package org.swimming.labs.message.jedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import redis.clients.jedis.Jedis;

public class BadConcurrentJedisTest1 {

	private static final ExecutorService pool = Executors.newCachedThreadPool();

	private static final Jedis jedis = new Jedis("localhost", 6379);

	static {
		jedis.connect();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			pool.execute(new RedisTest());
		}

	}

	static class RedisTest implements Runnable {

		@Override
		public void run() {
			while (true) {
				jedis.set("hello", "world");
			}
		}
	}

}