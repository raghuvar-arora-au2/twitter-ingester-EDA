package com.microservice.twittertokafkaservice.runner.implementations;

import java.util.HashSet;
import java.util.Set;

import com.microservice.twittertokafkaservice.listener.TwitterToKafkaStatusListener;
import com.microservice.twittertokafkaservice.runner.StreamRunner;


import org.springframework.stereotype.Component;

import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Component
public class TwitterStreamRunner implements StreamRunner  {

    private final TwitterToKafkaStatusListener twitterToKafkaStatusListener;

    public TwitterStreamRunner(TwitterToKafkaStatusListener twitterToKafkaStatusListener) {
        this.twitterToKafkaStatusListener = twitterToKafkaStatusListener;
        
    }



    @Override
    public void run() throws TwitterException {
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        String[] keywords = { "Covid", "hello" };
        twitterStream.addListener(twitterToKafkaStatusListener);
        twitterStream.filter(keywords);
        twitterStream.sample();
    }

}
