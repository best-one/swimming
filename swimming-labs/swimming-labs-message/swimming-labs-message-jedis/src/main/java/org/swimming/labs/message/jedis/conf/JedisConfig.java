package org.swimming.labs.message.jedis.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Slf4j
@Configuration
public class JedisConfig {

    @Value("${spring.redis.host:localhost}")
    private String redisHost;

    @Value("${spring.redis.port:6379}")
    private int redisPort;

//    @Value("${spring.redis.password}")
//    private String redisPassword;

    @Value("${spring.redis.minIdle:5}")
    private int minIdle;

    @Value("${spring.redis.maxIdle:20}")
    private int maxIdle;

    @Value("${spring.redis.maxWaitMillis:600}")
    private int maxWaitMillis;

    @Value("${spring.redis.maxTotal:20}")
    private int maxTotal;

    @Value("${spring.redis.blockWhenExhausted:true}")
    private boolean blockWhenExhausted;
    
    @Bean
    @Primary
    public JedisPool makeJedisPool() {

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        poolConfig.setMaxTotal(maxTotal);
        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        poolConfig.setBlockWhenExhausted(blockWhenExhausted);
        return new JedisPool(poolConfig, redisHost, redisPort);

    }


    @Bean
    public Jedis makeJedis(JedisPool jedisPool){
        Jedis jedis = jedisPool.getResource();
        jedis.clientSetname("websocketService");
        
        log.info("jedis==null::{}",jedis==null);
        log.info("jedis.isConnected()::{}",jedis.isConnected());
        log.info("jedis.ping().equals(\"PONG\")::{}", jedis.ping().equals("PONG"));
        
        return jedis;
    }
}
