package com.remotegroup.procurement.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan({"com.remotegroup"})
@EntityScan({"com.remotegroup.procurement.contact.domain", "com.remotegroup.procurement.supplier.domain"})
@EnableJpaRepositories({"com.remotegroup.procurement.contact.persistence", "com.remotegroup.procurement.supplier.persistence"})
public class ProcurementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcurementApplication.class, args);
	}

}
