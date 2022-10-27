package com.remotegroup.inventory.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.remotegroup.inventory.product.domain.Product;
import com.remotegroup.inventory.product.persistence.ProductRepository;

@Component
public class KafkaListeners {

    private final ProductRepository productRepository;

    KafkaListeners(ProductRepository p){
        this.productRepository = p;
    }
    
    @SendTo ("productBySaleFromInventory")
    @KafkaListener(topics = "productBySaleFromSales", groupId = "productBySaleFromSales")
    Product listener(String data){
        Long data2 = Long.parseLong(data);
		return productRepository.findById(data2).get();
    }
}
