package com.springboot.hystrix.SpringBootHystrixApp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Ashish Gupta
 *
 */
@Service
public class HystrixService {

	
	@Autowired
	private RestTemplate restTemplate;

	
	@HystrixCommand(fallbackMethod="callEmployeeService_fallback")
	public String callEmployeeService(String empName) {		
		String url = "http://localhost:8070/employees/employee/{empName}";
		String response  = restTemplate.exchange(url, HttpMethod.GET,null, new ParameterizedTypeReference<String>() {},empName).getBody();
		System.out.println("response-->"+response+" Date "+new Date());
		return response;
		
	}
	
	@SuppressWarnings("unused")
	private String callEmployeeService_fallback(String empName){		
		String message = "CIRCUIT BREAKER ENABLED!!!,Employee Service will be available shortly "+new Date();
		return message;
		
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
}
