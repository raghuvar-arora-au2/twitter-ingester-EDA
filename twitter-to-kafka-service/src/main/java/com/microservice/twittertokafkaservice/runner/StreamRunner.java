package com.microservice.twittertokafkaservice.runner;

import twitter4j.TwitterException;

public interface StreamRunner {
    public void run() throws TwitterException;
}
