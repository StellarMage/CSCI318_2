package com.remotegroup.sales.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remotegroup.sales.controller.Controller;
import com.remotegroup.sales.sale.domain.Sale;
import com.remotegroup.sales.sale.persistence.SaleRepository;
import com.remotegroup.sales.service.SaleService;
import com.remotegroup.sales.service.SaleServiceImpl;
import com.remotegroup.sales.shareddomain.BusinessIntelligence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;

@SpringBootApplication
@ComponentScan({"com.remotegroup"})
@EntityScan({"com.remotegroup.sales.sale.domain", "com.remotegroup.sales.backordersale.domain", "com.remotegroup.sales.instoresale.domain", "com.remotegroup.sales.onlinesale.domain", "com.remotegroup.sales.store.domain"})
@EnableJpaRepositories({"com.remotegroup.sales.sale.persistence", "com.remotegroup.sales.backordersale.persistence", "com.remotegroup.sales.instoresale.persistence", "com.remotegroup.sales.onlinesale.persistence", "com.remotegroup.sales.store.persistence"})
public class SalesApplication {

	private static final Logger log = LoggerFactory.getLogger(SalesApplication.class);
	/*private ObjectMapper mapper = new ObjectMapper();
  	private Controller controller;
	private SaleRepository saleRepository;
	private SaleServiceImpl saleServiceImpl;*/
	@Autowired SaleService saleService;

	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void loadDatabase() throws JsonProcessingException {
		saleService.initSaleBI();
	}
}
