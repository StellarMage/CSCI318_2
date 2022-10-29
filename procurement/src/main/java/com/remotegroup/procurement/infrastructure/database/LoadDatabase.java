package com.remotegroup.procurement.infrastructure.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.remotegroup.procurement.domain.model.aggregates.Contact;
import com.remotegroup.procurement.domain.model.aggregates.Supplier;
import com.remotegroup.procurement.infrastructure.persistence.ContactRepository;
import com.remotegroup.procurement.infrastructure.persistence.SupplierRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ContactRepository cRepository, SupplierRepository sRepository) {

    return args -> {
    	Supplier s = new Supplier("Pear", "Wollongong");
      log.info("Preloading " + sRepository.save(s));
      log.info("Preloading " + cRepository.save(new Contact(s.getSupplierId(),"Jim Davis", "0408459354", "jim@email.com", "Executive")));
    };
  }
}