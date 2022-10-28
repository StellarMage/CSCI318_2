package com.remotegroup.inventory.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.remotegroup.inventory.product.domain.Product;
import com.remotegroup.inventory.product.persistence.ProductRepository;

@Component
public class KafkaListeners {

    private final ProductRepository productRepository;
    private static final Logger log = LoggerFactory.getLogger(KafkaListeners.class);

    KafkaListeners(ProductRepository p){
        this.productRepository = p;
    }
    
    @SendTo ("productBySaleFromInventory")
    @KafkaListener(topics = "productBySaleFromSales", groupId = "productBySaleFromSales", containerFactory = "factory")
    Product listener(String data){
        log.info("Message Received");
        Long data2 = Long.parseLong(data);
		return productRepository.findById(data2).get();
    }
}
