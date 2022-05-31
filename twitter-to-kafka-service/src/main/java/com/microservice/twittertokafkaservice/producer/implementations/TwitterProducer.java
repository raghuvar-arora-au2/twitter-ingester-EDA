package com.microservice.twittertokafkaservice.producer.implementations;

import com.microservice.twittertokafkaservice.producer.Producer;
import com.twitter.clientlib.model.StreamingTweet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@PropertySource("classpath:kafka.properties")
@Component
public class TwitterProducer implements Producer<StreamingTweet> {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topic;

    TwitterProducer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    @Override
    public void send(StreamingTweet message) {
        
        this.kafkaTemplate.send(topic, message.toJson());
        
    }
    
}
