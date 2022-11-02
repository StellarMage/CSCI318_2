package com.remotegroup.businessintelligence.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger log = LoggerFactory.getLogger(BIService.class);
	BIService(BusinessIntelligenceRepository bIRepo){
		this.bIRepo = bIRepo;
	}
	
	@Override
	public void deleteBusinessIntelligence(BusinessIntelligenceId id){
		BusinessIntelligence BusinessIntelligence = getBusinessIntelligenceById(id);
		
		bIRepo.delete(BusinessIntelligence);
	}

	@Override
	public BusinessIntelligence newBusinessIntelligence(CreateNewBusinessIntelligenceCommand bI){
		String businessIntelligenceIdStr = UUID.randomUUID().toString().toUpperCase();
		log.info("UUID: " + businessIntelligenceIdStr);
		bI.setBusinessIntelligenceId(businessIntelligenceIdStr);
		return bIRepo.save(new BusinessIntelligence(bI));
	}

	@Override
	public BusinessIntelligence newSaleBusinessIntelligence(CreateSaleBusinessIntelligenceCommand bI){
		String businessIntelligenceIdStr = UUID.randomUUID().toString().toUpperCase();
		log.info("UUID: " + businessIntelligenceIdStr);
		bI.setBusinessIntelligenceId(businessIntelligenceIdStr);
		return bIRepo.save(new BusinessIntelligence(bI));
	}

	@Override
	public BusinessIntelligence replaceBusinessIntelligence(UpdateBusinessIntelligenceCommand bI){
		BusinessIntelligence businessIntelligence = getBusinessIntelligenceById(new BusinessIntelligenceId(bI.getBusinessIntelligenceId()));
		
		//update businessIntelligence
		businessIntelligence.updateBusinessIntelligence(bI);
		
		//save to repository
		return bIRepo.save(businessIntelligence);
	}
	
	@Override
	public BusinessIntelligence getBusinessIntelligenceById(BusinessIntelligenceId id){
		List<BusinessIntelligence> bis = getBusinessIntelligences();
		return bis.stream().filter(bi->{return bi.getBusinessIntelligenceId().equals(id);}).findAny().orElseGet(null);
	}

	@Override
	public List<BusinessIntelligence> getBusinessIntelligences(){
		return bIRepo.findAll();
	}

	@Override
	public List<BusinessIntelligenceId> getBusinessIntelligenceIds(){
		List<BusinessIntelligenceId> ids = new ArrayList<BusinessIntelligenceId>();
		List<BusinessIntelligence> BusinessIntelligences = getBusinessIntelligences();
		for(int i=0;i<BusinessIntelligences.size();i++) {
			ids.add(BusinessIntelligences.get(i).getBusinessIntelligenceId());
		}
		return ids;
	}
}
