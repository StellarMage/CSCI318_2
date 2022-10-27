package com.remotegroup.sales.backordersale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class BackOrderSaleController {
	
	@Autowired
	SaleService saleService;
	
	//use case: get all BackOrderSales
	@GetMapping("/BackOrderSales")
	List<BackOrderSale> all(){
		return saleService.getBackOrderSales();
	}
	
	//use case: create BackOrderSale
	@PostMapping("/BackOrderSale")
	BackOrderSale newBackOrderSale(@RequestBody BackOrderSale backOrderSale) {
		return saleService.createBackOrderSale(backOrderSale);
	}
	
	//use case: delete BackOrderSale
	@DeleteMapping("/BackOrderSale/{id}")
	void deleteBackOrderSale(@PathVariable Long id) {
		saleService.deleteBackOrderSale(id);
	}
	
	//use case: get BackOrderSale by id
	@GetMapping("/BackOrderSale/{id}")
	BackOrderSale getBackOrderSaleById(@PathVariable Long id) throws BackOrderSaleNotFoundException {
		return saleService.getBackOrderSale(id);
	}
	
}
