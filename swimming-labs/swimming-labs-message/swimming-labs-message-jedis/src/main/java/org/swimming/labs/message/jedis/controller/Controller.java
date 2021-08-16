package org.swimming.labs.message.jedis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

@Slf4j
@RestController
public class Controller {

	@Autowired
	Jedis jedis;
    @GetMapping("/")
	public String feng() throws Exception {
		log.info("jedis==null::{}", jedis == null);
		log.info("jedis.isConnected()::{}", jedis.isConnected());
		log.info("jedis.ping().equals(\"PONG\")::{}", jedis.ping().equals("PONG"));
		log.info("========================================");
        if(jedis==null || !jedis.isConnected()){
            throw new Exception("jedis is not connected ...");
        }
		return "feef";
	}
}
