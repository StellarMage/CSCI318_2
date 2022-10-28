package com.remotegroup.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v11/messages")
public class Controller {
    
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Controller() {
    }

    public Controller(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String data) {
        kafkaTemplate.send("productBySaleFromSales", data);
    }
}
