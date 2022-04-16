package com.springboot.hystrix.SpringBootHystrixApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableCircuitBreaker
@EnableHystrixDashboard
@SpringBootApplication
public class SpringBootHystrixAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHystrixAppApplication.class, args);
	}

}