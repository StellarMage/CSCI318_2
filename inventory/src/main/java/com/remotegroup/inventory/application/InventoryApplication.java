package com.remotegroup.inventory.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan({"com.remotegroup"})
@EntityScan({"com.remotegroup.inventory.part.domain", "com.remotegroup.inventory.product.domain"})
@EnableJpaRepositories({"com.remotegroup.inventory.part.persistence", "com.remotegroup.inventory.product.persistence"})
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

}
