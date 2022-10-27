package com.remotegroup;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v11/messages")
public class Controller {
    
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Controller(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(@RequestBody Request request) {
        kafkaTemplate.send("remotegroup", request.message());
    }
}
