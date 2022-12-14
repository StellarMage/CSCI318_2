package com.remotegroup.businessintelligence.businessIntelligence.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.remotegroup.businessintelligence.businessIntelligence.domain.BusinessIntelligence;
import com.remotegroup.businessintelligence.businessIntelligence.persistence.BusinessIntelligenceRepository;
import com.remotegroup.businessintelligence.exceptions.BusinessIntelligenceNotFoundException;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class BusinessIntelligenceController {
	
	//static
	private static BusinessIntelligenceRepository businessIntelligenceRepository;

	BusinessIntelligenceController(BusinessIntelligenceRepository businessIntelligenceRepository) {
		BusinessIntelligenceController.businessIntelligenceRepository = businessIntelligenceRepository;
	}

	//See all past BusinessIntelligence
	@GetMapping("/businesses")
	List<BusinessIntelligence> all() {
		return businessIntelligenceRepository.findAll();
	}
	
	//Create a BusinessIntelligence
	@PostMapping("/business")
	public static
	BusinessIntelligence newBusinessIntelligence(@RequestBody BusinessIntelligence businessIntelligence) {
		return businessIntelligenceRepository.save(businessIntelligence);
	}
	

	//Edit a BusinessIntelligence
	@PutMapping("/business/{id}")
    public static
	//public static
	BusinessIntelligence replaceBusinessIntelligence(@RequestBody BusinessIntelligence newBusinessIntelligence, @PathVariable Long id) {
		return businessIntelligenceRepository.findById(id)
		      	.map(BusinessIntelligence -> {
					BusinessIntelligence.setName(newBusinessIntelligence.getName());
					BusinessIntelligence.setQuantity(newBusinessIntelligence.getQuantity());
		            BusinessIntelligence.setPrice(newBusinessIntelligence.getPrice());
		        return businessIntelligenceRepository.save(BusinessIntelligence);
		      })
		      	.orElseGet(() -> {
		        	newBusinessIntelligence.setId(id);
		        	return businessIntelligenceRepository.save(newBusinessIntelligence);
		      });
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
