package com.remotegroup.sales.infrastructure.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.remotegroup.sales.domain.model.commands.CreateSaleCommand;
import com.remotegroup.sales.domain.model.commands.CreateStoreCommand;
import com.remotegroup.sales.domain.model.services.ISaleService;
import com.remotegroup.sales.infrastructure.persistence.BackOrderSaleRepository;
import com.remotegroup.sales.infrastructure.persistence.InStoreSaleRepository;
import com.remotegroup.sales.infrastructure.persistence.OnlineSaleRepository;
import com.remotegroup.sales.infrastructure.persistence.SaleRepository;
import com.remotegroup.sales.infrastructure.persistence.StoreRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
  
  @Autowired
  ISaleService service;

  @Bean
  CommandLineRunner initDatabase(BackOrderSaleRepository bRepository, InStoreSaleRepository iRepository, OnlineSaleRepository oRepository, 
		  SaleRepository saRepository, StoreRepository stRepository) {

	  return args -> {
		  
		  CreateStoreCommand c1 = new CreateStoreCommand("1313 Disneyland Dr, Anaheim, CA 92802", "Walt Disney");
		  CreateStoreCommand c2 = new CreateStoreCommand("3/125 Beach Rd, 5165, Christies Beach", "Jamie Oliver");
		  
		  log.info("Preloading store:  "+service.createStore(c1));
		  log.info("Preloading store:  "+service.createStore(c2));

      CreateSaleCommand c3 = new CreateSaleCommand("3100ED61-F572-46E3-8C6D-7E115E451710", "Marin Road Bike", "3", "31-10-2022", "1499.00");
		  CreateSaleCommand c4 = new CreateSaleCommand("842CFC2A-49DC-4BC7-B854-A9072E45B6E9", "Touring Mountain Bike", "2", "31-10-2022", "2599");
		  
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