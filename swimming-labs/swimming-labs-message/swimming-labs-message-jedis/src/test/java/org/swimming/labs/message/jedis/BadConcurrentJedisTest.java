package org.swimming.labs.message.jedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import redis.clients.jedis.Jedis;

/**
 * jedis 不要多线程共享示例，会出现问题
 * @author jim
 *
 */
public class BadConcurrentJedisTest {

	private static final ExecutorService pool = Executors.newFixedThreadPool(20);

	private static  final Jedis jedis = new Jedis("localhost", 6379);

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			pool.execute(new RedisSet());
		}
	}

	static class RedisSet implements Runnable {

		@Override
		public void run() {
			while (true) {
				jedis.set("hello", "world");
			}
		}

	}
}