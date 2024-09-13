package com.kafka.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("/")
public class MyController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping
    public ResponseEntity<String> publishMessage() {
        kafkaProducerService
                .send("general-task-topic", "demo-key",
                        new TaskStatus("taskId" + LocalTime.now().getSecond(), "taskName", 50.0f, TaskStatus.Status.RUNNING));
        return ResponseEntity.ok("pushed");
    }
}
