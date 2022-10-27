package com.remotegroup.sales.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan({"com.remotegroup"})
@EntityScan({"com.remotegroup.sales.sale.domain", "com.remotegroup.sales.backordersale.domain", "com.remotegroup.sales.instoresale.domain", "com.remotegroup.sales.onlinesale.domain", "com.remotegroup.sales.store.domain"})
@EnableJpaRepositories({"com.remotegroup.sales.sale.persistence", "com.remotegroup.sales.backordersale.persistence", "com.remotegroup.sales.instoresale.persistence", "com.remotegroup.sales.onlinesale.persistence", "com.remotegroup.sales.store.persistence"})
public class SalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}

}
