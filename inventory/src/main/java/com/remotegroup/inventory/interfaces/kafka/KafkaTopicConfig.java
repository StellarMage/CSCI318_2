package com.remotegroup.inventory.interfaces.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    
    @Bean
    public NewTopic productBySaleFromSalesTopic() {
        return TopicBuilder.name("productBySaleFromSales")
                .build();

    }

    @Bean
    public NewTopic productBySaleFromInventoryTopic() {
        return TopicBuilder.name("productBySaleFromInventory")
                .build();

    }

    @Bean
    public NewTopic procRequestItP() {
        return TopicBuilder.name("procRequestItP")
                .build();

    }
}
