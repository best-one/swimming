package com.gg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gg"})
public class Start {

	public static void main(String[] args) {
		SpringApplication.run(Start.class, args);
	}
}
