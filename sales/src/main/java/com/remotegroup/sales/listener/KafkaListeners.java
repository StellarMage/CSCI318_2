package com.remotegroup.sales.listener;

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
    private ObjectMapper mapper = new ObjectMapper();

    public KafkaListeners(){
    }
    
    @KafkaListener(topics = "productBySaleFromInventory", groupId = "productBySaleFromInventory")
    void listener(String data) throws JsonMappingException, JsonProcessingException{
        Product dataReceived = mapper.readValue(data, Product.class);
		this.dataReceived = dataReceived;
    }

    public Product getListener(){
        return dataReceived;
    }
}
