package com.remotegroup.businessintelligence.domain.model.aggregates;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class BusinessIntelligenceId implements Serializable {

	private String businessIntelligenceId;
	
	BusinessIntelligenceId(){}
	
	public BusinessIntelligenceId(String id) {this.businessIntelligenceId = id;}
	
	public String toString() {return this.businessIntelligenceId;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		BusinessIntelligenceId e = (BusinessIntelligenceId) o;
		return businessIntelligenceId.equals(e.businessIntelligenceId);
	}

	public String getValue(){
		return businessIntelligenceId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(businessIntelligenceId);
	}
}