package com.springboot.kafka.app.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
class KafkaStreamNumberConsumer {
	
	@Value("${myapp.kafka.stream.output.topic}")
	private String kafkaStreamOutputTopic;
	public void receive(String value) {
		System.out.println("Received Number "+value);		
	}
}

