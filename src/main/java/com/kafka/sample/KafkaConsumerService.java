package com.kafka.sample;

import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumerService {

    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 3000, multiplier = 1.5, maxDelay = 15000),
            exclude = {NullPointerException.class, IOException.class}
    )
    @KafkaListener(topics = "my-topic-01", groupId = "my-cg-01")
    public void listenGroup1(TaskStatus message,
                             @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                             @Header(KafkaHeaders.OFFSET) long offset) {
        System.out.println("Consume message............................... " + topic + ", " + offset);
        int a = 1;
        if (a == 1) {
            throw new RuntimeException("consumer exception");
        }
        System.out.println("Received message in group1: " + message);
    }

    // for multiple consumer
//    @KafkaListener(topics = "my-topic-01", groupId = "my-cg-01")
//    public void listenGroup2(TaskStatus message) {
//        System.out.println("Received message in group2: " + message);
//    }
//
//    @KafkaListener(topics = "my-topic-01", groupId = "my-cg-01")
//    public void listenGroup3(TaskStatus message) {
//        System.out.println("Received message in group3: " + message);
//    }


    @DltHandler
    public void dlt(TaskStatus message,
                    @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                    @Header(KafkaHeaders.OFFSET) long offset) {
        System.out.println("Consume message............................... " + topic + ", " + offset);
    }
}
