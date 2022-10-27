package com.remotegroup.procurement;

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
public class SupplierController {
	
	@Autowired
	ProcurementService procurementService;
	
	//use case: get all suppliers.
	@GetMapping("/suppliers")
	List<Supplier> getSuppliers() {
		return procurementService.getSuppliers();
	}
	
	//use case: create supplier
	@PostMapping("/supplier")
	Supplier createSupplier(@RequestBody Supplier supplier) {
		return procurementService.createSupplier(supplier);
	}
	
	//use case: update supplier
	@PutMapping("/supplier/{id}")
	Supplier updateSupplier(@RequestBody Supplier newSupplier, @PathVariable Long id) {
		return procurementService.updateSupplier(newSupplier, id);
	}
	
	//use case: delete supplier
	@DeleteMapping("/supplier/{id}")
	void deleteSupplier(@PathVariable Long id) {
		procurementService.deleteSupplier(id);
	}
	
	//use case: get supplier by id
	@GetMapping("/supplier/{id}")
	Supplier getSupplier(@PathVariable Long id) {
		return procurementService.getSupplier(id);
	}
	
}
