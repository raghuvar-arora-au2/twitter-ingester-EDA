package com.microservice.twittertokafkaservice.configuration;

import com.twitter.clientlib.TwitterCredentialsOAuth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:twitter.properties")
public class TwitterConfiguration {
    @Value("${oauth.consumerKey}")
    private String consumerKey;

    @Value("${oauth.consumerSecret}")
    private String consumerSecret;

    @Value("${oauth.accessToken}")
    private String accessToken;

    @Value("${oauth.accessTokenSecret}")
    private String accessTokenSecret;

    static TwitterCredentialsOAuth2 credentials;

    TwitterConfiguration(){
        credentials=new TwitterCredentialsOAuth2(consumerKey, consumerSecret, accessToken , accessTokenSecret);
    }

    public TwitterCredentialsOAuth2 getCredentials(){
        return credentials;
    }
}
