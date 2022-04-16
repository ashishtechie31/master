package com.springboot.kafka.app.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author Ashish Gupta
 * com.springboot.kafka.app
 */
@Repository
public class MessageRepository {

	private List<String> list = new ArrayList<>();
	
	public void addMessage(String message) {
		list.add(message);		
	}
	
	public String getAllMessage(){
		return list.toString();	
	}
}
