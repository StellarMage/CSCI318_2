package com.remotegroup.businessintelligence.domain.model.services;

import java.util.List;

import com.remotegroup.businessintelligence.domain.model.aggregates.BusinessIntelligence;
import com.remotegroup.businessintelligence.domain.model.aggregates.BusinessIntelligenceId;
import com.remotegroup.businessintelligence.domain.model.commands.CreateNewBusinessIntelligenceCommand;
import com.remotegroup.businessintelligence.domain.model.commands.CreateSaleBusinessIntelligenceCommand;
import com.remotegroup.businessintelligence.domain.model.commands.UpdateBusinessIntelligenceCommand;

public interface IBIService {
	
	//expose use case abilities for business intelligence
	public abstract void deleteBusinessIntelligence(BusinessIntelligenceId id);
	public abstract BusinessIntelligence newBusinessIntelligence(CreateNewBusinessIntelligenceCommand bI);
	public abstract BusinessIntelligence newSaleBusinessIntelligence(CreateSaleBusinessIntelligenceCommand bI);
	public abstract BusinessIntelligence replaceBusinessIntelligence(UpdateBusinessIntelligenceCommand bI);
	
	public abstract BusinessIntelligence getBusinessIntelligenceById(BusinessIntelligenceId id);
	public abstract List<BusinessIntelligence> getBusinessIntelligences();
	List<BusinessIntelligenceId> getBusinessIntelligenceIds();
}
