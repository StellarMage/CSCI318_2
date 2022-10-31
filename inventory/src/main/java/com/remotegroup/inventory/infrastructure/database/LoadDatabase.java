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
import com.remotegroup.inventory.domain.model.aggregates.Part;
import com.remotegroup.inventory.domain.model.aggregates.Product;
import com.remotegroup.inventory.domain.model.commands.CreatePartCommand;
import com.remotegroup.inventory.domain.model.commands.CreateProductCommand;
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
			JsonNode supplierIds = restTemplate.getForObject(url, JsonNode.class);
			log.info("Preloaded supplierIds:  " + supplierIds);
		  
			List<SupplierId> ids = mapper.convertValue(supplierIds, new TypeReference<List<SupplierId>>() {});
			log.info("Preloaded ids:  " + ids);
			//assuming 2 suppliers
			CreatePartCommand com1 = new CreatePartCommand(ids.get(0).toString(),"Bike Frame", "The frame of a bike.", 657);
			CreatePartCommand com2 = new CreatePartCommand(ids.get(1).toString(), "Bike Wheel", "The wheel for a bike.", 1453);
			
			Part part1 = service.createPart(com2);
			Part part2 = service.createPart(com1);
			
			log.info("Preloaded Part:  " + part1);
			log.info("Preloaded Part:  " + part2);
			
			String[][] bikeParts = {{part1.getPartId().toString(), "1"},{part2.getPartId().toString(), "2"}};
			
			CreateProductCommand com3 = new CreateProductCommand("Marin Road Bike", "$1499.00", "-", bikeParts, 8);
			CreateProductCommand com4 = new CreateProductCommand("Touring Mountain Bike", "$2599.00", "-", bikeParts, 2);
			CreateProductCommand com5 = new CreateProductCommand("Basic 1-Speed Bike", "$399.00", "-", bikeParts, 20);
			
			
			Product product1 = service.createProduct(com3);
			Product product2 = service.createProduct(com4);
			Product product3 = service.createProduct(com5);
			
			log.info("Preloaded Product:  "+product1);
			log.info("Preloaded Product:  "+product2);
			log.info("Preloaded Product:  "+product3);
		  
	  };
  }
}