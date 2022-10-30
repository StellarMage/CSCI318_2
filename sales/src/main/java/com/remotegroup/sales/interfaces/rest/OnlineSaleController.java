package com.remotegroup.sales.interfaces.rest;

import com.remotegroup.sales.domain.model.aggregates.OnlineSale;
import com.remotegroup.sales.domain.model.commands.CreateOnlineSaleCommand;
import com.remotegroup.sales.domain.model.services.ISaleService;

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

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class OnlineSaleController {
	
	@Autowired
	ISaleService saleService;
	
	//use case: get all OnlineSales.
	@GetMapping("/OnlineSales")
	List<OnlineSale> all() {
		return saleService.getOnlineSales();
	}
	
	//use case: create OnlineSale
	@PostMapping("/OnlineSale")
	OnlineSale newOnlineSale(@RequestBody CreateOnlineSaleCommand OnlineSale) {
		if(saleService.requestCheckInventory(OnlineSale.getItemId())) {
			return saleService.createSale(OnlineSale);
		}else {
			return null;
		}
	}
	

	//use case: update OnlineSale
	@PutMapping("/OnlineSale/{id}")
	OnlineSale replaceOnlineSale(@RequestBody OnlineSale newOnlineSale, @PathVariable Long id) {
		return saleService.updateSale(newOnlineSale, id);
	}
	
	//use case: delete OnlineSale
	@DeleteMapping("/OnlineSale/{id}")
	void deleteOnlineSale(@PathVariable Long id) {
		saleService.deleteOnlineSale(id);
	}
	
	//use case: get OnlineSale by id
	@GetMapping("/OnlineSale/{id}")
	OnlineSale getOnlineSaleById(@PathVariable Long id) {
		return saleService.getOnlineSale(id);
	}
	
}