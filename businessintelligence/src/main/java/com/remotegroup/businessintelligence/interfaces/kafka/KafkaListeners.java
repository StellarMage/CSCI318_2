package com.remotegroup.businessintelligence.interfaces.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remotegroup.businessintelligence.domain.model.aggregates.BusinessIntelligence;
import com.remotegroup.businessintelligence.domain.model.commands.CreateSaleBusinessIntelligenceCommand;
import com.remotegroup.businessintelligence.infrastructure.persistence.BusinessIntelligenceRepository;
import com.remotegroup.businessintelligence.interfaces.rest.BusinessIntelligenceController;
import com.remotegroup.shareddomain.model.aggregates.Sale;

@Component
public class KafkaListeners {

    private static final Logger log = LoggerFactory.getLogger(KafkaListeners.class);
    private ObjectMapper mapper = new ObjectMapper();
    private BusinessIntelligenceRepository biRepository;

    public KafkaListeners(){
    }
    
    @KafkaListener(topics = "bIInit", groupId = "bIInit")
    void bIInitListener(String data) throws JsonMappingException, JsonProcessingException{
        log.info("JSON String Received");
        Sale dataReceived = mapper.readValue(data, Sale.class);
        log.info("Converting JtS: " + dataReceived);
        CreateSaleBusinessIntelligenceCommand c = new CreateSaleBusinessIntelligenceCommand(dataReceived);
        BusinessIntelligence dataConverted = new BusinessIntelligence(c);
        log.info("Converting StB: " + dataConverted);
        log.info("Receiving: " + biRepository.save(dataConverted));
    }

    @KafkaListener(topics = "bISendSale", groupId = "bISendSale")
    void bISendSaleListener(String data) throws JsonMappingException, JsonProcessingException{
        log.info("JSON String Received");
        Sale dataReceived = mapper.readValue(data, Sale.class);
        log.info("Converting JtS: " + dataReceived);
        CreateSaleBusinessIntelligenceCommand c = new CreateSaleBusinessIntelligenceCommand(dataReceived);
        BusinessIntelligence dataConverted = new BusinessIntelligence(c);
        log.info("Converting StB: " + dataConverted);
        log.info("Receiving: " + biRepository.save(dataConverted));
    }

    /*  As sale is no longer updated neither is BI
    @KafkaListener(topics = "bISendUpdateSale", groupId = "bISendUpdateSale")
    void bISendUpdateSaleListener(String data) throws JsonMappingException, JsonProcessingException{
        log.info("JSON String Received");
        Sale dataReceived = mapper.readValue(data, Sale.class);
        log.info("Converting JtS: " + dataReceived);
        CreateSaleBusinessIntelligenceCommand c = new CreateSaleBusinessIntelligenceCommand(dataReceived);
        BusinessIntelligence dataConverted = new BusinessIntelligence(c);
        log.info("Converting StB: " + dataConverted);
        //Add Update Code
        log.info("Receiving: ");
        BusinessIntelligence bI = dataConverted;
        BusinessIntelligenceController.replaceBusinessIntelligence(bI, bI.getId());
        
    }*/
}
