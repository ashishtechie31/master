package com.springboot.kafka.app.config;

import java.security.SecureRandom;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NumberProducer {
	
	@Scheduled(fixedDelay=2000)
	public void generateNumber() {
		SecureRandom random  = new SecureRandom();
		random.nextInt(10000);		
	}
}
