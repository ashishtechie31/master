package com.springboot.kafka.app.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RandomNumberProducer {

	private Logger log =LoggerFactory.getLogger(RandomNumberProducer.class);
	
	@Autowired
	private KafkaTemplate<String, Integer> kafkaTemplate;
	
	@Value("${myapp.kafka.stream.input.topic}")
	private String kafkaStreamInputTopic;
	
	public void produce(Integer randomNumber ) {
		String res= "Even";
		log.info("randomNumber Publish-->"+randomNumber);
		if(randomNumber%2==0) {
			kafkaTemplate.send(kafkaStreamInputTopic,res,randomNumber);	
		}		
	}
}
