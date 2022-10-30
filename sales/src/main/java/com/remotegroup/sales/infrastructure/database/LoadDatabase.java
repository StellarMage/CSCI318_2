package com.remotegroup.sales.infrastructure.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.remotegroup.sales.domain.model.aggregates.BackOrderSale;
import com.remotegroup.sales.domain.model.aggregates.InStoreSale;
import com.remotegroup.sales.domain.model.aggregates.OnlineSale;
import com.remotegroup.sales.domain.model.aggregates.Sale;
import com.remotegroup.sales.domain.model.aggregates.Store;
import com.remotegroup.sales.infrastructure.persistence.BackOrderSaleRepository;
import com.remotegroup.sales.infrastructure.persistence.InStoreSaleRepository;
import com.remotegroup.sales.infrastructure.persistence.OnlineSaleRepository;
import com.remotegroup.sales.infrastructure.persistence.SaleRepository;
import com.remotegroup.sales.infrastructure.persistence.StoreRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(BackOrderSaleRepository bRepository, InStoreSaleRepository iRepository, OnlineSaleRepository oRepository, SaleRepository saRepository, StoreRepository stRepository) {

    return args -> {
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