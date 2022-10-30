package com.remotegroup.procurement.interfaces.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remotegroup.shareddomain.model.aggregates.BackOrderSale;

@Component
public class KafkaListeners {

    private static final Logger log = LoggerFactory.getLogger(KafkaListeners.class);
    private ObjectMapper mapper = new ObjectMapper();

    public KafkaListeners(){
    }
    
    @KafkaListener(topics = "procRequestItP", groupId = "procRequestItP")
    void listener(String data) throws JsonMappingException, JsonProcessingException{
        log.info("JSON String Received");
        BackOrderSale dataReceived = mapper.readValue(data, BackOrderSale.class);
        log.info("Converting JSON String to Product Info");
		System.out.println("Event: " + dataReceived);
        log.info("Event: " + dataReceived);
    }
}
