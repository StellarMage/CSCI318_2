package com.remotegroup.businessintelligence.exceptions;

public class BusinessIntelligenceNotFoundException extends RuntimeException {
	public BusinessIntelligenceNotFoundException(Long id){
		super("Could not find business intelligence "+id);
	}
}