package com.remotegroup.sales.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.remotegroup.sales.shareddomain.Product;

@Component
public class KafkaListeners {

    private Product dataReceived;

    public KafkaListeners(){
    }

    KafkaListeners(Product p){
        this.dataReceived = p;
    }
    
    @KafkaListener(topics = "productBySaleFromInventory", groupId = "productBySaleFromInventory")
    void listener(Product data){
		this.dataReceived = data;
    }

    public Product getListener(){
        return dataReceived;
    }
}
