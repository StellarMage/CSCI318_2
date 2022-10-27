package com.remotegroup.sales.sale.controller;

import java.util.List;
import java.util.function.Function;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.swing.JOptionPane;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.remotegroup.inventory.Product;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class SaleController {
	
	@Autowired
	SaleService salesService;
	
	//use case: get all sales.
	@GetMapping("/sales")
	List<Sale> all() {
		return salesService.getSales();
	}
	
	//use case: create sale
	// CHECK INVENTORY
	@PostMapping("/sale")
	Sale newSale(@RequestBody Sale sale) {
		if(salesService.requestCheckInventory(sale.getItemId())) {
			return salesService.createSale(sale);	
		}else {
			return null;
		}
	}
	
	//use case: update sale
	@PutMapping("/sale/{id}")
	Sale replaceSale(@RequestBody Sale newSale, @PathVariable Long id) {
		return salesService.updateSale(newSale, id);
	}
	
	//use case: delete sale
	@DeleteMapping("/sale/{id}")
	void deleteSale(@PathVariable Long id) {
		salesService.deleteSale(id);
	}
	
	//use case: get sale by id
	@GetMapping("/sale/{id}")
	Sale getSaleById(@PathVariable Long id) {
		return salesService.getSale(id);
	}

	//use case get product by id
	//use case: get sale by id
	@GetMapping("/sale/product/{id}")
	Product getProductInfo(@PathVariable Long id) {
		return salesService.getProductInfo(id);
	}
};