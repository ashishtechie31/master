package com.springboot.kafka.app.processor;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamProcessor {

	@Value("${myapp.kafka.stream.input.topic}")
	private String kafkaStreamInputTopic;
	
	@Value("${myapp.kafka.stream.output.topic}")
	private String kafkaStreamOutputTopic;
	
	   @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
	    public KafkaStreamsConfiguration config(KafkaProperties kafkaProperties) {
	        Map<String, Object> config = new HashMap<>();
	        System.out.println("client ID "+kafkaProperties.getClientId());
	        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
	        config.put(StreamsConfig.APPLICATION_ID_CONFIG, kafkaProperties.getClientId());//1234
	        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
	        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass());
	        return new KafkaStreamsConfiguration(config);
	    }
	   // move this code to consumer new class.
	@Bean
	public KStream<String, Integer> evenNumberStream(StreamsBuilder streamsBuilder) {
		KStream<String, Integer> input = streamsBuilder.stream(kafkaStreamInputTopic);
		KStream<String,Integer> output = input.filter((k,v)->k.equals("Even"));
		System.out.println("Inside KafkaStreamProcessor"+output.toString());
		output.to(kafkaStreamOutputTopic);
		return output;
	}
}