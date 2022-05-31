package com.microservice.twittertokafkaservice.producer;

public interface Producer<T> {
    public void send(T t);
}
