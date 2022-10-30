package com.remotegroup.inventory.interfaces.rest.controllers;

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
import com.remotegroup.inventory.domain.model.aggregates.Part;
import com.remotegroup.inventory.domain.model.aggregates.PartId;
import com.remotegroup.inventory.domain.model.commands.CreatePartCommand;
import com.remotegroup.inventory.domain.model.commands.UpdatePartCommand;
import com.remotegroup.inventory.domain.model.services.IInventoryService;
import com.remotegroup.inventory.domain.model.valueobjects.SupplierId;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class PartController {
	
	@Autowired
	IInventoryService inventoryService;
	
	PartController() {}

	//use case: get all parts.
	@GetMapping("/parts")
	List<Part> all() {
		return inventoryService.getParts();
	}
	
	//use case: create part
	@PostMapping("/part")
	Part newPart(@RequestBody CreatePartCommand c) {
		return inventoryService.createPart(c);
	}
	

	//use case: update part
	@PutMapping("/part/{id}")
	Part replacePart(@RequestBody UpdatePartCommand c) {
		return inventoryService.updatePart(c);
	}
	
	//use case: delete part
	@DeleteMapping("/part/{id}")
	void deletePart(@PathVariable String id) {
		inventoryService.deletePart(new PartId(id));
	}
	
	//use case: get part by id
	@GetMapping("/part/{id}")
	Part one(@PathVariable String id) {
		return inventoryService.getPart(new PartId(id));
	}

	//use case: look up supplier by part
	@GetMapping("/part/supplier/{id}")
	SupplierId getPartSupplier(@PathVariable String id) {
		return inventoryService.getPartSupplier(new PartId(id));
	}

}
