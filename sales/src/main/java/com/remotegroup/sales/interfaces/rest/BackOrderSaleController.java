package com.remotegroup.sales.interfaces.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.remotegroup.sales.domain.model.aggregates.BackOrderSale;
import com.remotegroup.sales.domain.model.aggregates.SaleId;
import com.remotegroup.sales.domain.model.commands.CreateBackOrderSaleCommand;
import com.remotegroup.sales.domain.model.services.ISaleService;
import com.remotegroup.sales.exceptions.BackOrderSaleNotFoundException;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class BackOrderSaleController {
	
	@Autowired
	ISaleService saleService;
	
	//use case: get all BackOrderSales
	@GetMapping("/BackOrderSales")
	List<BackOrderSale> all(){
		return saleService.getBackOrderSales();
	}
	
	//use case: create BackOrderSale
	@PostMapping("/BackOrderSale")
	BackOrderSale newBackOrderSale(@RequestBody CreateBackOrderSaleCommand c) throws JsonProcessingException {
		return saleService.createBackOrderSale(c);
	}
	
	//use case: delete BackOrderSale
	@DeleteMapping("/BackOrderSale/{id}")
	void deleteBackOrderSale(@PathVariable String id) {
		saleService.deleteBackOrderSale(new SaleId(id));
	}
	
	//use case: get BackOrderSale by id
	@GetMapping("/BackOrderSale/{id}")
	BackOrderSale getBackOrderSaleById(@PathVariable String id) throws BackOrderSaleNotFoundException {
		return saleService.getBackOrderSale(new SaleId(id));
	}
	
}
