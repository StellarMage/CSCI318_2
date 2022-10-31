package com.remotegroup.businessintelligence.interfaces.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.remotegroup.businessintelligence.domain.model.aggregates.BusinessIntelligence;
import com.remotegroup.businessintelligence.domain.model.aggregates.BusinessIntelligenceId;
import com.remotegroup.businessintelligence.domain.model.commands.CreateNewBusinessIntelligenceCommand;
import com.remotegroup.businessintelligence.domain.model.commands.CreateSaleBusinessIntelligenceCommand;
import com.remotegroup.businessintelligence.domain.model.commands.UpdateBusinessIntelligenceCommand;
import com.remotegroup.businessintelligence.domain.model.services.IBIService;

@RestController
public class BusinessIntelligenceController {
	
	@Autowired
	IBIService biService;

	BusinessIntelligenceController() {}

	//See all past BusinessIntelligence
	@GetMapping("/businesses")
	List<BusinessIntelligence> all() {
		return biService.all();
	}
	
	//Create a BusinessIntelligence
	@PostMapping("/business")
	BusinessIntelligence newBusinessIntelligence(@RequestBody CreateNewBusinessIntelligenceCommand bI) {
		return biService.newBusinessIntelligence(bI);
	}

	@PostMapping("/business")
	BusinessIntelligence newSaleBusinessIntelligence(@RequestBody CreateSaleBusinessIntelligenceCommand bI) {
		return biService.newSaleBusinessIntelligence(bI);
	}
	

	//Edit a BusinessIntelligence
	@PutMapping("/business/{id}")
	BusinessIntelligence replaceBusinessIntelligence(UpdateBusinessIntelligenceCommand updateBusinessIntelligenceCommand) {
		return biService.replaceBusinessIntelligence(updateBusinessIntelligenceCommand);
	}
	
	//Delete a BusinessIntelligence
	@DeleteMapping("/business/{id}")
	void deleteBusinessIntelligence(@PathVariable BusinessIntelligenceId id) {
		biService.deleteBusinessIntelligence(id);
	}
	
	//Find a BusinesIntelligence
	@GetMapping("/business/{id}")
	BusinessIntelligence getBusinessIntelligenceById(@PathVariable BusinessIntelligenceId id) {
		return biService.getBusinessIntelligenceById(id);
	}

}
