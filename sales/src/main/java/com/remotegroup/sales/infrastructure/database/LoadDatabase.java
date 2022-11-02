package com.remotegroup.sales.infrastructure.database;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.remotegroup.sales.domain.model.commands.CreateSaleCommand;
import com.remotegroup.sales.domain.model.commands.CreateStoreCommand;
import com.remotegroup.sales.domain.model.services.ISaleService;
import com.remotegroup.sales.infrastructure.persistence.BackOrderSaleRepository;
import com.remotegroup.sales.infrastructure.persistence.InStoreSaleRepository;
import com.remotegroup.sales.infrastructure.persistence.OnlineSaleRepository;
import com.remotegroup.sales.infrastructure.persistence.SaleRepository;
import com.remotegroup.sales.infrastructure.persistence.StoreRepository;
import com.remotegroup.shareddomain.model.aggregates.Product;
import com.remotegroup.shareddomain.model.aggregates.ProductId;
import com.remotegroup.shareddomain.model.valueobjects.SupplierId;

@Configuration
class LoadDatabase {
  
  @Autowired
  ISaleService service;

  @Bean
  CommandLineRunner initDatabase(BackOrderSaleRepository bRepository, InStoreSaleRepository iRepository, OnlineSaleRepository oRepository, 
		  SaleRepository saRepository, StoreRepository stRepository, RestTemplateBuilder restTemplateBuilder) {
      RestTemplate restTemplate = restTemplateBuilder.build();
      Logger log = LoggerFactory.getLogger(LoadDatabase.class);
      ObjectMapper mapper = new ObjectMapper();
      Random random = new Random();

	  return args -> {
		  
		  CreateStoreCommand c1 = new CreateStoreCommand("1313 Disneyland Dr, Anaheim, CA 92802", "Walt Disney");
		  CreateStoreCommand c2 = new CreateStoreCommand("3/125 Beach Rd, 5165, Christies Beach", "Jamie Oliver");
		  
		  log.info("Preloading store:  "+service.createStore(c1));
		  log.info("Preloading store:  "+service.createStore(c2));

		  String url = "http://localhost:8081/products/ids";
      JsonNode productIds = restTemplate.getForObject(url, JsonNode.class);
      List<ProductId> ids = mapper.convertValue(productIds, new TypeReference<List<ProductId>>() {});
      
      log.info("Preloading ids:  "+ ids);

      Integer min = 0;
      Integer max = ids.size() - 1;
      ProductId rand1 = ids.get(random.nextInt(max - min) + min);
      ProductId rand2 = ids.get(random.nextInt(max - min) + min);

      log.info("Preloading ProductId:  " + rand1);
		  log.info("Preloading ProductId:  " + rand2);

      String pUrl1 = "http://localhost:8081/product/" + rand1.toString();
      String pUrl2 = "http://localhost:8081/product/" + rand2.toString();

      log.info("Preloading ProductUrl:  " + pUrl1);
		  log.info("Preloading ProductUrl:  " + pUrl2);

      Product prod1 = restTemplate.getForObject(pUrl1, Product.class);
      Product prod2 = restTemplate.getForObject(pUrl2, Product.class);

      /*JsonNode jProd1 = restTemplate.getForObject(pUrl1, JsonNode.class);
      JsonNode jProd2 = restTemplate.getForObject(pUrl2, JsonNode.class);

      log.info("jProd1: " + jProd1);
      log.info("jProd2: " + jProd2);

      Product prod1 = mapper.convertValue(jProd1, new TypeReference<Product>() {});
      Product prod2 = mapper.convertValue(jProd2, new TypeReference<Product>() {});*/

      log.info("Preloading Product:  " + prod1);
		  log.info("Preloading Product:  " + prod2);

      CreateSaleCommand c3 = new CreateSaleCommand(rand1.toString(), prod1.getName().toString(), "3", "31-10-2022", prod1.getPrice().toString());
		  CreateSaleCommand c4 = new CreateSaleCommand(rand2.toString(), prod2.getName().toString(), "2", "31-10-2022", prod2.getPrice().toString());
		  
		  log.info("Preloading sale:  "+service.createSale(c3));
		  log.info("Preloading sale:  "+service.createSale(c4));
    	
     /* Store store = new Store("Store1", "Mike");
      Sale sale = new Sale((long)3, "Bike1", 2, "22-08-2022", 200);
      
      log.info("Preloading " + stRepository.save(store));
      log.info("Preloading " + saRepository.save(sale));
      log.info("Preloading " + iRepository.save(new InStoreSale((long)3, "Bike1", 2, "22-08-2022", store.getStoreId(), "R-0", 200)));
      log.info("Preloading " + oRepository.save(new OnlineSale((long)3, "Bike1", 2, "22-08-2022", "John", "Antarctica", 200)));
      log.info("Preloading " + bRepository.save(new BackOrderSale((long)3, "Bike1", 2, "22-08-2022", "+61134564351", 200)));*/
    };
  }
}