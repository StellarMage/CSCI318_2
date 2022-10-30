package com.remotegroup.shareddomain.model.aggregates;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

//aggregate id 
@Embeddable
public class OnlineSaleId implements Serializable{

	private String onlinesaleId;
	
	OnlineSaleId(){}
	
	public OnlineSaleId(String id) {this.onlinesaleId = id;}
	
	public String toString() {return this.onlinesaleId;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		OnlineSaleId e = (OnlineSaleId) o;
		return onlinesaleId.equals(e.onlinesaleId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(onlinesaleId);
	}
}