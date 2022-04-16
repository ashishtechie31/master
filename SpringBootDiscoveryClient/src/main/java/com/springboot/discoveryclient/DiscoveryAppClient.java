package com.springboot.discoveryclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Discovery Client.
 * https://cloud.spring.io/spring-cloud-static/spring-cloud-netflix/2.0.0.RELEASE/single/spring-cloud-netflix.html
 * @author Ashish Gupta 
 */
@SpringBootApplication
//@EnableZuulProxy
@EnableEurekaClient
public class DiscoveryAppClient 
{
    public static void main( String[] args )
    {
        SpringApplication.run(DiscoveryAppClient.class,args);
    }
}
