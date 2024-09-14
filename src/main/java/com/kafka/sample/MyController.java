package com.kafka.sample;

import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/")
public class MyController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping
    public ResponseEntity<String> publishMessage() {
        int i = 100;
        kafkaProducerService
                .send("my-topic-01", "demo-key" + i,
                        new TaskStatus(i + "-taskId" + LocalTime.now().getSecond(), "taskName", 50.0f,
                                TaskStatus.Status.RUNNING));
        return ResponseEntity.ok("pushed");
    }

    @Autowired
    KafkaAdmin kafkaAdmin;

    @GetMapping("/d")
    public void fun(@RequestParam("topic") String topic) {
        System.out.println(topic);
        Map<String, Object> configs = kafkaAdmin.getConfigurationProperties();
        try (AdminClient adminClient = AdminClient.create(configs)) {
            adminClient.deleteTopics(Collections.singletonList(topic)).all().get();
            System.out.println("Topic " + topic + " deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to delete topic " + topic);
        }
    }
}
