package com.springboot.mongodb.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
/***
 * 
 * @author Ashish Gupta
 *  Main Class for Spring Boot Repositories.
 *
 */
@SpringBootApplication
@EnableCaching
@EnableMongoRepositories
public class SpringBootMongoDBApp {
	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootMongoDBApp.class, args);
	}
}
