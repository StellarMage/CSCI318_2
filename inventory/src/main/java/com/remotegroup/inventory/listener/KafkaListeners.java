package com.remotegroup.inventory.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remotegroup.inventory.product.domain.Product;
import com.remotegroup.inventory.product.persistence.ProductRepository;

@Component
public class KafkaListeners {

    private final ProductRepository productRepository;
    private static final Logger log = LoggerFactory.getLogger(KafkaListeners.class);
    private ObjectMapper mapper = new ObjectMapper();

    KafkaListeners(ProductRepository p){
        this.productRepository = p;
    }
    
    @SendTo ("productBySaleFromInventory")
    @KafkaListener(topics = "productBySaleFromSales", groupId = "productBySaleFromSales", containerFactory = "factory")
    String listener(String data) throws JsonProcessingException{
        log.info("Message Received");
        Long data2 = Long.parseLong(data);
        String jsonString = mapper.writeValueAsString(productRepository.findById(data2).get());
		return jsonString;
    }
}
