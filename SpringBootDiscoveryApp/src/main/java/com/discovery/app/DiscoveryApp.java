package com.discovery.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * 
 * @author Ashish Gupta
 * Spring Boot Discovery Clients.
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryApp {
//https://lalverma.medium.com/spring-boot-microservices-implementing-service-discovery-cfc98e49b74f#:~:text=Registering%20Microservices&text=As%20Spring%20Boot%20finds%20the,automatically%20to%20the%20Eureka%20Server.&text=Step%202%3A%20Add%20src%2Fmain,with%20this%20name(id).
	public static void main(String[] args) {		
		SpringApplication.run(DiscoveryApp.class, args);
	}

}
