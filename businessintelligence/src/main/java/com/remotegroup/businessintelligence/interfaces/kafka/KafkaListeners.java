package com.remotegroup.businessintelligence.interfaces.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.remotegroup.businessintelligence.domain.model.aggregates.BusinessIntelligence;
import com.remotegroup.businessintelligence.domain.model.commands.CreateSaleBusinessIntelligenceCommand;
import com.remotegroup.businessintelligence.domain.model.services.IBIService;
import com.remotegroup.businessintelligence.infrastructure.persistence.BusinessIntelligenceRepository;
import com.remotegroup.shareddomain.events.SaleEvent;
import com.remotegroup.shareddomain.model.aggregates.Sale;

@Component
public class KafkaListeners {

    private static final Logger log = LoggerFactory.getLogger(KafkaListeners.class);
    private ObjectMapper mapper = new ObjectMapper();
    
    @Autowired private BusinessIntelligenceRepository biRepository;

    @Autowired IBIService service;

    public KafkaListeners(){
    }
    
    @KafkaListener(topics = "bIInit", groupId = "bIInit")
    void bIInitListener(String data){
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        log.info("JSON String Received");
        SaleEvent dataReceived;
        try {
            dataReceived = mapper.readValue(data, SaleEvent.class);
            log.info("Converting JtS: " + dataReceived);
            CreateSaleBusinessIntelligenceCommand c = new CreateSaleBusinessIntelligenceCommand(dataReceived);
            BusinessIntelligence dataConverted = service.newSaleBusinessIntelligence(c);
            log.info("Converting StB: " + dataConverted);
            log.info("Receiving: " + biRepository.save(dataConverted));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "bISendSale", groupId = "bISendSale")
    void bISendSaleListener(String data){
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        log.info("JSON String Received");
        SaleEvent dataReceived;
        try {
            dataReceived = mapper.readValue(data, SaleEvent.class);
            log.info("Converting JtS: " + dataReceived);
        CreateSaleBusinessIntelligenceCommand c = new CreateSaleBusinessIntelligenceCommand(dataReceived);
        BusinessIntelligence dataConverted = service.newSaleBusinessIntelligence(c);
        log.info("Converting StB: " + dataConverted);
        log.info("Receiving: " + biRepository.save(dataConverted));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
