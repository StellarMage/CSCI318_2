package com.remotegroup.sales.interfaces.rest;

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
import com.remotegroup.sales.domain.model.aggregates.InStoreSale;
import com.remotegroup.sales.domain.model.aggregates.SaleId;
import com.remotegroup.sales.domain.model.aggregates.StoreId;
import com.remotegroup.sales.domain.model.commands.CreateInStoreSaleCommand;
import com.remotegroup.sales.domain.model.commands.UpdateInStoreSaleCommand;
import com.remotegroup.sales.domain.model.services.ISaleService;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class InStoreSaleController {
	
	@Autowired
	ISaleService saleService;
	
	//use case: get all InStoreSales.
	@GetMapping("/InStoreSales")
	List<InStoreSale> all() {
		return saleService.getInStoreSales();
	}
	
	//use case: create InStoreSale
	@PostMapping("/InStoreSale")
	InStoreSale newInStoreSale(@RequestBody CreateInStoreSaleCommand c) throws JsonProcessingException {
		if(saleService.requestCheckInventory(c.getItemId())) {
			return saleService.createSale(c);
		}else {
			return null;
		}
		

	}
	

	//use case: update InStoreSale
	@PutMapping("/InStoreSale")
	InStoreSale replaceInStoreSale(@RequestBody UpdateInStoreSaleCommand c) {
		return saleService.updateSale(c);
	}
	
	//use case: delete InStoreSale
	@DeleteMapping("/InStoreSale/{id}")
	void deleteInStoreSale(@PathVariable String id) {
		saleService.deleteInStoreSale(new SaleId(id));
	}
	
	//use case: get InStoreSale by id
	@GetMapping("/InStoreSale/{id}")
	InStoreSale getInStoreSaleById(@PathVariable String id) {
		return saleService.getInStoreSale(new SaleId(id));
	}
	
	//use case: get InStoreSale by Store
	@GetMapping("/InStoreSales/{storeId}")
	List<InStoreSale> getSalesByStore(@PathVariable String storeId){
		return saleService.lookupSalesByStore(new StoreId(storeId));
	}
	
}
