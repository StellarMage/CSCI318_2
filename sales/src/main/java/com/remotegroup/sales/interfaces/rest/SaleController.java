package com.remotegroup.sales.interfaces.rest;

import com.remotegroup.sales.domain.model.aggregates.Sale;
import com.remotegroup.sales.domain.model.aggregates.SaleId;
import com.remotegroup.sales.domain.model.commands.CreateSaleCommand;
import com.remotegroup.sales.domain.model.commands.UpdateSaleCommand;
import com.remotegroup.sales.domain.model.services.ISaleService;
import com.remotegroup.shareddomain.model.aggregates.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class SaleController {
	
	@Autowired
	ISaleService salesService;
	
	//use case: get all sales.
	@GetMapping("/sales")
	List<Sale> all() {
		return salesService.getSales();
	}
	
	//use case: create sale
	// CHECK INVENTORY
	@PostMapping("/sale")
	Sale newSale(@RequestBody CreateSaleCommand s) throws JsonProcessingException {
		if(salesService.requestCheckInventory(s.getItemId())) {
			return salesService.createSale(s);	
		}else {
			return null;
		}
	}
	
	//use case: update sale
	@PutMapping("/sale/{id}")
	Sale replaceSale(@RequestBody UpdateSaleCommand s) {
		return salesService.updateSale(s);
	}
	
	//use case: delete sale
	@DeleteMapping("/sale/{id}")
	void deleteSale(@PathVariable String id) {
		salesService.deleteSale(new SaleId(id));
	}
	
	//use case: get sale by id
	@GetMapping("/sale/{id}")
	Sale getSaleById(@PathVariable String id) {
		return salesService.getSale(new SaleId(id));
	}

	//use case get product by id
	//use case: get sale by id
	@GetMapping("/sale/product/{id}")
	Product getProductInfo(@PathVariable String id) {
		return salesService.getProductInfo(new SaleId(id));
	}

	@GetMapping("/sales/ids")
	List<SaleId> getSaleIds(){
		return salesService.getSaleIds();
	}
};