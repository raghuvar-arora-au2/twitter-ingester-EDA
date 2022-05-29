package com.microservice.twittertokafkaservice.listener;
import org.springframework.stereotype.Component;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

@Component
public class TwitterToKafkaStatusListener implements StatusListener {

    @Override
    public void onException(Exception ex) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onStatus(Status status) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onScrubGeo(long userId, long upToStatusId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onStallWarning(StallWarning warning) {
        // TODO Auto-generated method stub
        
    }
    
}
