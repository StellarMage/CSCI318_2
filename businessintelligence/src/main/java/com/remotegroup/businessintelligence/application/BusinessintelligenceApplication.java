package com.remotegroup.businessintelligence.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan({"com.remotegroup"})
@EntityScan("com.remotegroup.businessintelligence.businessIntelligence.domain.aggregates")
@EnableJpaRepositories("com.remotegroup.businessintelligence.infrastructure.persistence")
public class BusinessintelligenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessintelligenceApplication.class, args);
	}

}