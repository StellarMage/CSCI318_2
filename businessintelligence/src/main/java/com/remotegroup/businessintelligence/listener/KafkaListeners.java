package com.remotegroup.businessintelligence.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remotegroup.businessintelligence.businessIntelligence.domain.BusinessIntelligence;
import com.remotegroup.businessintelligence.businessIntelligence.persistence.BusinessIntelligenceRepository;

@Component
public class KafkaListeners {

    private BusinessIntelligence dataReceived;
    private static final Logger log = LoggerFactory.getLogger(KafkaListeners.class);
    private ObjectMapper mapper = new ObjectMapper();
    private BusinessIntelligenceRepository biRepository;

    public KafkaListeners(){
    }
    
    @KafkaListener(topics = "businessIntelligence", groupId = "businessIntelligence")
    void listener(String data) throws JsonMappingException, JsonProcessingException{
        log.info("JSON String Received");
        BusinessIntelligence dataReceived = mapper.readValue(data, BusinessIntelligence.class);
        log.info("Converting JSON String to Business Intelligence");
        log.info("Receiving: " + biRepository.save(dataReceived));
    }
}
