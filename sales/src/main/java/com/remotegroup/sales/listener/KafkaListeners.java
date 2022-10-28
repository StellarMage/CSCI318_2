package com.remotegroup.sales.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remotegroup.sales.shareddomain.Product;

@Component
public class KafkaListeners {

    private Product dataReceived;
    private static final Logger log = LoggerFactory.getLogger(KafkaListeners.class);
    private ObjectMapper mapper = new ObjectMapper();

    public KafkaListeners(){
    }
    
    @KafkaListener(topics = "productBySaleFromInventory", groupId = "productBySaleFromInventory")
    void listener(String data) throws JsonMappingException, JsonProcessingException{
        log.info("JSON String Received");
        Product dataReceived = mapper.readValue(data, Product.class);
        log.info("Converting JSON String to Product Info");
		this.dataReceived = dataReceived;
        log.info("Product Info Received");
    }

    public Product getListener(){
        return dataReceived;
    }
}
