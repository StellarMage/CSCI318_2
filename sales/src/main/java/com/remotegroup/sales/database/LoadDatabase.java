package com.remotegroup.sales.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.remotegroup.sales.backordersale.domain.*;
import com.remotegroup.sales.backordersale.persistence.*;
import com.remotegroup.sales.instoresale.domain.*;
import com.remotegroup.sales.instoresale.persistence.*;
import com.remotegroup.sales.onlinesale.domain.*;
import com.remotegroup.sales.onlinesale.persistence.*;
import com.remotegroup.sales.sale.domain.*;
import com.remotegroup.sales.sale.persistence.*;
import com.remotegroup.sales.store.domain.*;
import com.remotegroup.sales.store.persistence.*;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(BackOrderSaleRepository bRepository, InStoreSaleRepository iRepository, OnlineSaleRepository oRepository, SaleRepository saRepository, StoreRepository stRepository) {

    return args -> {
      Store store = new Store("Store1", "Mike");
      
      log.info("Preloading " + stRepository.save(store));
      log.info("Preloading " + saRepository.save(new Sale((long)5, "Bike1", 2, "22-08-2022", 200)));
      log.info("Preloading " + iRepository.save(new InStoreSale((long)5, "Bike1", 2, "22-08-2022", store.getStoreId(), "R-0", 200)));
      log.info("Preloading " + oRepository.save(new OnlineSale((long)5, "Bike1", 2, "22-08-2022", "John", "Antarctica", 200)));
      log.info("Preloading " + bRepository.save(new BackOrderSale((long)5, "Bike1", 2, "22-08-2022", "+61134564351", 200)));
    };
  }
}