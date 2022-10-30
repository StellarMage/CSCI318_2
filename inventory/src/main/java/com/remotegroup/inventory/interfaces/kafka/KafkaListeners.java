package com.remotegroup.inventory.interfaces.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remotegroup.inventory.domain.model.aggregates.ProductId;
import com.remotegroup.inventory.domain.model.services.IInventoryService;
import com.remotegroup.inventory.infrastructure.persistence.ProductRepository;

@Component
public class KafkaListeners {

    @Autowired
    IInventoryService inventoryService;

    private static final Logger log = LoggerFactory.getLogger(KafkaListeners.class);
    private ObjectMapper mapper = new ObjectMapper();
    
    @SendTo ("productBySaleFromInventory")
    @KafkaListener(topics = "productBySaleFromSales", groupId = "productBySaleFromSales", containerFactory = "factory")
    String listener(String data) throws JsonProcessingException{
        log.info("Product Info by Sale Request Received");
        ProductId data2 = mapper.readValue(data, ProductId.class);
        String jsonString = mapper.writeValueAsString(inventoryService.getProduct(data2));
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
