package com.remotegroup.inventory.infrastructure.database;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remotegroup.inventory.domain.model.commands.CreatePartCommand;
import com.remotegroup.inventory.domain.model.services.IInventoryService;
import com.remotegroup.inventory.domain.model.valueobjects.SupplierId;
import com.remotegroup.inventory.infrastructure.persistence.PartRepository;
import com.remotegroup.inventory.infrastructure.persistence.ProductRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
  
  @Autowired IInventoryService service;
  
  @Bean
  CommandLineRunner initDatabase(ProductRepository prRepository, PartRepository paRepository, RestTemplateBuilder restTemplateBuilder) {
	  RestTemplate restTemplate = restTemplateBuilder.build();
	  
	  return args -> {
			ObjectMapper mapper = new ObjectMapper();
		  	String url = "http://localhost:8082/suppliers/ids";
		  
		  	JsonNode ids = restTemplate.getForObject(url, JsonNode.class);
			List<SupplierId> supplierId = mapper.convertValue(ids, new TypeReference<List<SupplierId>>() {});
		  	//assuming 2 suppliers
		  	CreatePartCommand com1 = new CreatePartCommand(supplierId.get(0).toString(),"Bike Frame", "The frame of a bike.", 657);
		  	CreatePartCommand com2 = new CreatePartCommand(supplierId.get(1).toString(), "Bike Wheel", "The wheel for a bike.", 1453);
		  	log.info("Preloaded Part:  " + service.createPart(com1));
		  	log.info("Preloaded Part:  " + service.createPart(com2));;
	  };
  }
}