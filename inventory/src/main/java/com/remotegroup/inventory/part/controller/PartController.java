package com.remotegroup.inventory.part.controller;

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
import com.remotegroup.inventory.part.domain.Part;
import com.remotegroup.inventory.service.InventoryService;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class PartController {
	
	@Autowired
	InventoryService inventoryService;
	
	//use case: get all parts.
	@GetMapping("/parts")
	List<Part> all() {
		return inventoryService.getParts();
	}
	
	//use case: create part
	@PostMapping("/part")
	Part newPart(@RequestBody Part part) {
		return inventoryService.createPart(part);
	}
	

	//use case: update part
	@PutMapping("/part/{id}")
	Part replacePart(@RequestBody Part newPart, @PathVariable Long id) {
		return inventoryService.updatePart(newPart, id);
	}
	
	//use case: delete part
	@DeleteMapping("/part/{id}")
	void deletePart(@PathVariable Long id) {
		inventoryService.deletePart(id);
	}
	
	//use case: get part by id
	@GetMapping("/part/{id}")
	Part getPartById(@PathVariable Long id) {
		return inventoryService.getPart(id);
	}

	//use case: look up supplier by part
	@GetMapping("/part/supplier/{id}")
	Long getPartSupplier(@PathVariable Long id) {
		return inventoryService.getPartSupplier(id);
	}

}
