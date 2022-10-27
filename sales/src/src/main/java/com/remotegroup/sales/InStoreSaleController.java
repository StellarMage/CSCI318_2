package com.remotegroup.sales;

import java.util.List;
import java.util.function.Function;

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
public class InStoreSaleController {
	
	@Autowired
	SaleService saleService;
	
	//use case: get all InStoreSales.
	@GetMapping("/InStoreSales")
	List<InStoreSale> all() {
		return saleService.getInStoreSales();
	}
	
	//use case: create InStoreSale
	@PostMapping("/InStoreSale")
	InStoreSale newInStoreSale(@RequestBody InStoreSale inStoreSale) {
		if(saleService.requestCheckInventory(inStoreSale.getItemId())) {
			return saleService.createSale(inStoreSale);
		}else {
			return null;
		}
		

	}
	

	//use case: update InStoreSale
	@PutMapping("/InStoreSale/{id}")
	InStoreSale replaceInStoreSale(@RequestBody InStoreSale newInStoreSale, @PathVariable Long id) {
		return saleService.updateSale(newInStoreSale, id);
	}
	
	//use case: delete InStoreSale
	@DeleteMapping("/InStoreSale/{id}")
	void deleteInStoreSale(@PathVariable Long id) {
		saleService.deleteInStoreSale(id);
	}
	
	//use case: get InStoreSale by id
	@GetMapping("/InStoreSale/{id}")
	InStoreSale getInStoreSaleById(@PathVariable Long id) {
		return saleService.getInStoreSale(id);
	}
	
	//use case: get InStoreSale by Store
	@GetMapping("/InStoreSales/{storeId}")
	List<InStoreSale> getSalesByStore(@PathVariable Long storeId){
		return saleService.lookupSalesByStore(storeId);
	}
	
}
