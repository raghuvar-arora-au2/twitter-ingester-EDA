package com.microservice.twittertokafkaservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


import com.microservice.twittertokafkaservice.runner.implementations.TwitterStreamRunner;

// import twitter4j.*;

@ComponentScan(basePackages = {"com.microservice.twittertokafkaservice"})
@SpringBootApplication
public class TwitterToKafkaServiceApplication implements CommandLineRunner {
	// private TwitterToKafkaStatusListener twitterToKafkaStatusListener;
	TwitterStreamRunner twitterStreamRunner;
	public static void main(String[] args) {
		SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
		
		// TwitterStreamRunner runner= 
	}

	public TwitterToKafkaServiceApplication(TwitterStreamRunner streamRunner){
		this.twitterStreamRunner=streamRunner;
	}

	@Override
	public void run(String... args) throws Exception {
		
		this.twitterStreamRunner.run();
		
	}

	

}
