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
    String pBSFIListener(String data) throws JsonProcessingException{
        log.info("Product Info by Sale Request Received");
        Long data2 = Long.parseLong(data);
        String jsonString = mapper.writeValueAsString(productRepository.findById(data2).get());
        log.info("Converting Product Info to JSON String");
        log.info("JSON String Sent");
		return jsonString;
    }

    @SendTo ("procRequestItP")
    @KafkaListener(topics = "procRequestStI", groupId = "procRequestStI")
    String pRItPListener(String data) throws JsonProcessingException{
        log.info("JSON String Received");
		System.out.println("Event: " + data);
        log.info("Event: " + data);
        return data;
    }
}
