package com.springboot.kafka.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration(exclude={KafkaStreamsDefaultConfiguration.class})
//https://auth0.com/blog/spring-cloud-streams-with-apache-kafka/
//https://stackoverflow.com/questions/51733039/kafka-streams-with-spring-boot
//https://piotrminkowski.com/2021/11/11/kafka-streams-with-spring-cloud-stream/
//https://developer.confluent.io/learn-kafka/spring/hands-on-process-messages-with-kafka-streams/
//https://github.com/spring-cloud/spring-cloud-stream-samples/tree/main/kafka-streams-samples
public class SpringBootKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaApplication.class, args);
	}

}
