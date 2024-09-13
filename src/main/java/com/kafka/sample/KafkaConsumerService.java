package com.kafka.sample;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "my-topic-01", groupId = "my-cg-01")
    public void listenGroup1(TaskStatus message) {
        System.out.println("Received message in group1: " + message);
    }

    @KafkaListener(topics = "my-topic-01", groupId = "my-cg-01")
    public void listenGroup2(TaskStatus message) {
        System.out.println("Received message in group2: " + message);
    }

    @KafkaListener(topics = "my-topic-01", groupId = "my-cg-01")
    public void listenGroup3(TaskStatus message) {
        System.out.println("Received message in group3: " + message);
    }
}
