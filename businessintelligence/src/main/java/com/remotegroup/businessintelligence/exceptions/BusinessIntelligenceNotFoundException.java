package com.remotegroup.businessintelligence;

public class BusinessIntelligenceNotFoundException extends RuntimeException {
	public BusinessIntelligenceNotFoundException(Long id){
		super("Could not find business intelligence "+id);
	}
}