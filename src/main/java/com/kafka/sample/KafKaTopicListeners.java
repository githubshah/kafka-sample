package com.kafka.sample;

import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

//@Component
//@Log
//@KafkaListener(topics = "my-topic-01", groupId = "my-cg-01")
public class KafKaTopicListeners {

    private final Logger logger = LoggerFactory.getLogger(KafKaTopicListeners.class);

    @KafkaHandler
    public void handleMessage(TaskStatus taskStatus) {
        logger.info(String.format("Task status is updated : " + taskStatus));
    }

    @KafkaHandler(isDefault = true)
    public void handleMessage(@Payload Object message) {
        logger.info(String.format("Task status is updated : " + message.toString()));
    }
}