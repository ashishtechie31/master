package com.springboot.hystrix.SpringBootHystrixApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.hystrix.SpringBootHystrixApp.service.HystrixService;

/**
 * @author Ashish Gupta
 *
 */
@RestController
@RequestMapping(path="/appDetails")
public class HystrixController {

		//localhost:8090/appDetails/app/Ashish
		//http://localhost:8090/hystrix
		//http://localhost:8090/hystrix.stream
		//https://howtodoinjava.com/spring-cloud/spring-hystrix-circuit-breaker-tutorial/
			
	@Autowired
	private HystrixService hystrixService;
	
	@GetMapping(value="/app/{empName}")
	public String getAppName(@PathVariable String empName){
			
		 String response =  hystrixService.callEmployeeService(empName);
		return "Hystrix-->"+response;
	}

	public HystrixService getHystrixService() {
		return hystrixService;
	}

	public void setHystrixService(HystrixService hystrixService) {
		this.hystrixService = hystrixService;
	}
}
