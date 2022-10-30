package com.remotegroup.sales.interfaces.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaController() {
    }

    public KafkaController(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String data) {
        kafkaTemplate.send("productBySaleFromSales", data);
    }

    public void procure(String data) {
        kafkaTemplate.send("procRequestStI", data);
    }

    public void bIInit(String data) {
        kafkaTemplate.send("bIInit", data);
    }

    public void bISendSale(String data) {
        kafkaTemplate.send("bISendSale", data);
    }

    public void bISendUpdateSale(String data) {
        kafkaTemplate.send("bISendUpdateSale", data);
    }
}
