package com.springboot.kafka.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.kafka.app.producer.MessageProducer;
import com.springboot.kafka.app.repository.MessageRepository;

/**
 * @author Ashish
 *
 */

@RestController
@RequestMapping(path="/kafka")
public class KafkaController {
	
	@Autowired
	private MessageProducer messageProducer;
	
	@Autowired
	private MessageRepository messageRepository; 
	
	//localhost:9090/kafka/publish?message=Test
	@PostMapping(value="/publish")
	public String viewMessages(@RequestParam("message") String message) {
		messageProducer.sendMessage(message);		
		return "Message sent Successfully, "+message;
	}
	
	//localhost:9090/kafka/getAll
	@GetMapping(value="/getAll")
	public String getAllMessages() {		
		return messageRepository.getAllMessage();
	}

	public MessageProducer getMessageProducer() {
		return messageProducer;
	}

	public void setMessageProducer(MessageProducer messageProducer) {
		this.messageProducer = messageProducer;
	}

	public MessageRepository getMessageRepository() {
		return messageRepository;
	}

	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
}
