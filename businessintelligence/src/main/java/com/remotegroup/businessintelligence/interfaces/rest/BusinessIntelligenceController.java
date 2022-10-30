package com.remotegroup.businessintelligence.interfaces.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.remotegroup.businessintelligence.domain.model.aggregates.BusinessIntelligence;
import com.remotegroup.businessintelligence.domain.model.aggregates.BusinessIntelligenceId;
import com.remotegroup.businessintelligence.domain.model.commands.UpdateBusinessIntelligenceCommand;
import com.remotegroup.businessintelligence.exceptions.BusinessIntelligenceNotFoundException;
import com.remotegroup.businessintelligence.infrastructure.persistence.BusinessIntelligenceRepository;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class BusinessIntelligenceController {
	
	@Autowired
	static
	BusinessIntelligenceRepository businessIntelligenceRepository;

	//See all past BusinessIntelligence
	@GetMapping("/businesses")
	List<BusinessIntelligence> all() {
		return businessIntelligenceRepository.findAll();
	}
	
	//Create a BusinessIntelligence
	@PostMapping("/business")
	BusinessIntelligence newBusinessIntelligence(@RequestBody BusinessIntelligence businessIntelligence) {
		return businessIntelligenceRepository.save(businessIntelligence);
	}
	

	//Edit a BusinessIntelligence
	@PutMapping("/business/{id}")
	public static
	BusinessIntelligence replaceBusinessIntelligence(UpdateBusinessIntelligenceCommand updateBusinessIntelligenceCommand) {
		
		//find the businessIntelligence by businessIntelligenceId
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("businessIntelligenceId", match->match.exact());
		BusinessIntelligence bIExample = new BusinessIntelligence();
		bIExample.setBusinessIntelligenceId(new BusinessIntelligenceId(updateBusinessIntelligenceCommand.getBusinessIntelligenceId()));
		Example<BusinessIntelligence> example = Example.of(bIExample, matcher);
		
		//store businessIntelligence in object
		List<BusinessIntelligence> returnBusinessIntelligences =  businessIntelligenceRepository.findAll(example);
		BusinessIntelligence businessIntelligence = returnBusinessIntelligences.get(0);
		
		//update businessIntelligence
		businessIntelligence.updateBusinessIntelligence(updateBusinessIntelligenceCommand);
		
		//save to repository
		return businessIntelligenceRepository.save(businessIntelligence);
	}
	
	//Delete a BusinessIntelligence
	@DeleteMapping("/business/{id}")
	void deleteBusinessIntelligence(@PathVariable Long id) {
		businessIntelligenceRepository.deleteById(id);
	}
	
	//Find a BusinesIntelligence
	@GetMapping("/business/{id}")
	BusinessIntelligence getBusinessIntelligenceById(@PathVariable Long id) {
		try {
			return businessIntelligenceRepository.findById(id).get();
			
		}catch(Exception e) {
			throw new BusinessIntelligenceNotFoundException(id);
		}
	}

}
