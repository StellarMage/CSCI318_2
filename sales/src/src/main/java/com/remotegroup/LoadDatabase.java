package com.remotegroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*import com.remotegroup.inventory.Part;
import com.remotegroup.inventory.PartRepository;
import com.remotegroup.inventory.Product;
import com.remotegroup.inventory.ProductRepository;
import com.remotegroup.procurement.Contact;
import com.remotegroup.procurement.ContactRepository;
import com.remotegroup.procurement.Supplier;
import com.remotegroup.procurement.SupplierRepository;*/
import com.remotegroup.sales.backordersale.*;
//import com.remotegroup.sales.backordersalerepository;
import com.remotegroup.sales.instoresale.*;
//import com.remotegroup.sales.InStoreSaleRepository;
import com.remotegroup.sales.onlinesale.*;
//import com.remotegroup.sales.OnlineSaleRepository;
import com.remotegroup.sales.sale.*;
//import com.remotegroup.sales.SaleRepository;
import com.remotegroup.sales.store.*;
//import com.remotegroup.sales.StoreRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(/*ContactRepository cRepository, SupplierRepository sRepository, ProductRepository prRepository, PartRepository paRepository,*/ BackOrderSaleRepository bRepository, InStoreSaleRepository iRepository, OnlineSaleRepository oRepository, SaleRepository saRepository, StoreRepository stRepository) {

    return args -> {
    	Supplier s = new Supplier("Pear", "Wollongong");
      log.info("Preloading " + sRepository.save(s));
      log.info("Preloading " + cRepository.save(new Contact(s.getSupplierId(),"Jim Davis", "0408459354", "jim@email.com", "Executive")));
      Part pa1 = new Part(s.getSupplierId(), "Part1", "description", 5);
      Part pa2 = new Part(s.getSupplierId(), "Part2", "description2", 30);
      paRepository.save(pa1);
      paRepository.save(pa2);
      
      Long[][] l = {
        {pa1.getId(), (long) 5}
      };
      Store store = new Store("Store1", "Mike");
      
      log.info("Preloading " + prRepository.save(new Product("Bike1", 4.50, "comment", l, 7)));
      log.info("Preloading " + stRepository.save(store));
      log.info("Preloading " + saRepository.save(new Sale((long)5, "Bike1", 2, "22-08-2022")));
      log.info("Preloading " + iRepository.save(new InStoreSale((long)5, "Bike1", 2, "22-08-2022", store.getStoreId(), "R-0")));
      log.info("Preloading " + oRepository.save(new OnlineSale((long)5, "Bike1", 2, "22-08-2022", "John", "Antarctica")));
      log.info("Preloading " + bRepository.save(new BackOrderSale((long)5, "Bike1", 2, "22-08-2022", "+61134564351")));
    };
  }
}