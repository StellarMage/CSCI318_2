package com.remotegroup.inventory.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.remotegroup.inventory.part.domain.Part;
import com.remotegroup.inventory.part.persistence.PartRepository;
import com.remotegroup.inventory.product.domain.Product;
import com.remotegroup.inventory.product.persistence.ProductRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
  
  @Bean
  CommandLineRunner initDatabase(ProductRepository prRepository, PartRepository paRepository) {
	return null;

    /*return args -> {
      Part pa1 = new Part(s.getSupplierId(), "Part1", "description", 5);
      Part pa2 = new Part(s.getSupplierId(), "Part2", "description2", 30);
      paRepository.save(pa1);
      paRepository.save(pa2);
      
      Long[][] l = {
        {pa1.getId(), (long) 5}
      };
      
      log.info("Preloading " + prRepository.save(new Product("Bike1", 4.50, "comment", l, 7)));
    };*/
  }
}