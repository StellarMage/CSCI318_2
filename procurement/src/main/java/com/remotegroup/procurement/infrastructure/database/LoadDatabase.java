package com.remotegroup.procurement.infrastructure.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.remotegroup.procurement.application.services.ProcurementService;
import com.remotegroup.procurement.domain.model.aggregates.Contact;
import com.remotegroup.procurement.domain.model.aggregates.Supplier;
import com.remotegroup.procurement.domain.model.commands.CreateContactCommand;
import com.remotegroup.procurement.domain.model.commands.CreateSupplierCommand;
import com.remotegroup.procurement.infrastructure.persistence.ContactRepository;
import com.remotegroup.procurement.infrastructure.persistence.SupplierRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Autowired
  ProcurementService procurementService;

  @Bean
  CommandLineRunner initDatabase(ContactRepository cRepository, SupplierRepository sRepository) {

    return args -> {
    	CreateSupplierCommand sC1 = new CreateSupplierCommand("Pear", "Wollongong");
      Supplier s1 = procurementService.createSupplier(sC1);
      log.info("Preloading " + s1);

      CreateContactCommand cC1 = new CreateContactCommand(s1.getSupplierId().toString(),"0408459354", "jim@email.com", "Jim Davis", "Executive");
      Contact c1 = procurementService.createContact(cC1);
      log.info("Preloading " + c1);

      CreateContactCommand cC2 = new CreateContactCommand(s1.getSupplierId().toString(),"0408657158", "susan@email.com", "Susan Moore", "Employee");
      Contact c2 = procurementService.createContact(cC2);
      log.info("Preloading " + c2);

      CreateSupplierCommand sC2 = new CreateSupplierCommand("Orange", "Melbourne");
      Supplier s2 = procurementService.createSupplier(sC2);
      log.info("Preloading " + s2);

      CreateContactCommand cC3 = new CreateContactCommand(s2.getSupplierId().toString(),"0408265758", "george@email.com", "George Brown", "Auditor");
      Contact c3 = procurementService.createContact(cC3);
      log.info("Preloading " + c3);
    };
  }
}