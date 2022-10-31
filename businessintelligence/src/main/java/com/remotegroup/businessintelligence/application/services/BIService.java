package com.remotegroup.businessintelligence.application.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.remotegroup.businessintelligence.domain.model.aggregates.BusinessIntelligence;
import com.remotegroup.businessintelligence.domain.model.aggregates.BusinessIntelligenceId;
import com.remotegroup.businessintelligence.domain.model.commands.CreateNewBusinessIntelligenceCommand;
import com.remotegroup.businessintelligence.domain.model.commands.CreateSaleBusinessIntelligenceCommand;
import com.remotegroup.businessintelligence.domain.model.commands.UpdateBusinessIntelligenceCommand;
import com.remotegroup.businessintelligence.domain.model.services.IBIService;
import com.remotegroup.businessintelligence.infrastructure.persistence.BusinessIntelligenceRepository;

@Service
public class BIService implements IBIService{

	@Autowired private final BusinessIntelligenceRepository bIRepo;
	BIService(BusinessIntelligenceRepository bIRepo){
		this.bIRepo = bIRepo;
	}
	
	@Override
	public void deleteBusinessIntelligence(BusinessIntelligenceId id){
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("businessIntelligenceId", match->match.exact());
		BusinessIntelligence bIExample = new BusinessIntelligence();
		bIExample.setBusinessIntelligenceId(id);
		Example<BusinessIntelligence> example = Example.of(bIExample, matcher);
		
		//store supplier in object
		List<BusinessIntelligence> returnBusinessIntelligences =  bIRepo.findAll(example);
		BusinessIntelligence BusinessIntelligence = returnBusinessIntelligences.get(0);
		
		bIRepo.delete(BusinessIntelligence);
	}

	@Override
	public BusinessIntelligence newBusinessIntelligence(CreateNewBusinessIntelligenceCommand bI){
		String businessIntelligenceIdStr = UUID.randomUUID().toString().toUpperCase();
		bI.setBusinessIntelligenceId(businessIntelligenceIdStr);
		
		return bIRepo.save(new BusinessIntelligence(bI));
	}

	@Override
	public BusinessIntelligence newSaleBusinessIntelligence(CreateSaleBusinessIntelligenceCommand bI){
		String businessIntelligenceIdStr = UUID.randomUUID().toString().toUpperCase();
		bI.setBusinessIntelligenceId(businessIntelligenceIdStr);
		
		return bIRepo.save(new BusinessIntelligence(bI));
	}

	@Override
	public BusinessIntelligence replaceBusinessIntelligence(UpdateBusinessIntelligenceCommand bI){
		//find the businessIntelligence by businessIntelligenceId
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("businessIntelligenceId", match->match.exact());
		BusinessIntelligence bIExample = new BusinessIntelligence();
		bIExample.setBusinessIntelligenceId(new BusinessIntelligenceId(bI.getBusinessIntelligenceId()));
		Example<BusinessIntelligence> example = Example.of(bIExample, matcher);
		
		//store businessIntelligence in object
		List<BusinessIntelligence> returnBusinessIntelligences =  bIRepo.findAll(example);
		BusinessIntelligence businessIntelligence = returnBusinessIntelligences.get(0);
		
		//update businessIntelligence
		businessIntelligence.updateBusinessIntelligence(bI);
		
		//save to repository
		return bIRepo.save(businessIntelligence);
	}
	
	@Override
	public BusinessIntelligence getBusinessIntelligenceById(BusinessIntelligenceId id){
		//find the businessIntelligence by businessIntelligenceId
		ExampleMatcher matcher = ExampleMatcher.matching()
		.withMatcher("businessIntelligenceId", match->match.exact());
		BusinessIntelligence bIExample = new BusinessIntelligence();
		bIExample.setBusinessIntelligenceId(id);
		Example<BusinessIntelligence> example = Example.of(bIExample, matcher);

		//store businessIntelligence in object
		List<BusinessIntelligence> returnBusinessIntelligences =  bIRepo.findAll(example);
		BusinessIntelligence BusinessIntelligence = returnBusinessIntelligences.get(0);
		return BusinessIntelligence;
	}

	@Override
	public List<BusinessIntelligence> all(){
		return bIRepo.findAll();
	}
}
