package com.springboot.kafka.app.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.springboot.kafka.app.repository.MessageRepository;

@Component
public class MessageConsumer {

	private Logger log = LoggerFactory.getLogger(MessageConsumer.class);
	
	@Autowired
	private MessageRepository messageRepository;
	
	@KafkaListener(topics="${myapp.kafka.topic}",groupId="${myapp.kafka.group_id}")	
	public void consume(String message) {
		log.info("Message Received at consumer End: "+message);
		messageRepository.addMessage(message);
	}	
}
