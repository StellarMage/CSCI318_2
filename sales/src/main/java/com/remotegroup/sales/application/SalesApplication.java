package com.remotegroup.sales.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.remotegroup.sales.domain.model.services.ISaleService;

@SpringBootApplication
@ComponentScan({"com.remotegroup"})
@EntityScan({"com.remotegroup.sales.domain.model.aggregates"})
@EnableJpaRepositories({"com.remotegroup.sales.infrastructure.persistence"})
public class SalesApplication {

	private static final Logger log = LoggerFactory.getLogger(SalesApplication.class);
	/*private ObjectMapper mapper = new ObjectMapper();
  	private Controller controller;
	private SaleRepository saleRepository;
	private SaleServiceImpl saleServiceImpl;*/
	@Autowired ISaleService saleService;

	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void loadDatabase() throws JsonProcessingException {
		saleService.initSaleBI();
	}
}
