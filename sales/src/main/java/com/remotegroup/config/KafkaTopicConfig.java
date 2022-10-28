package com.remotegroup.config;

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
    public NewTopic procurement() {
        return TopicBuilder.name("procurement")
                .build();

    }

    @Bean
    public NewTopic businessIntelligence() {
        return TopicBuilder.name("businessIntelligence")
                .build();

    }
}
