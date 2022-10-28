package com.remotegroup.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    
    @Bean
    public NewTopic bIInit() {
        return TopicBuilder.name("bIInit")
                .build();

    }

    @Bean
    public NewTopic bISendSale() {
        return TopicBuilder.name("bISendSale")
                .build();

    }

    @Bean
    public NewTopic bISendUpdateSale() {
        return TopicBuilder.name("bISendUpdateSale")
                .build();

    }
}
