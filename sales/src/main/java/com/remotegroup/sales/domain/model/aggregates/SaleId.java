package com.remotegroup.sales.domain.model.aggregates;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

//aggregate id 
@Embeddable
public class SaleId implements Serializable{

	private String saleId;
	
	SaleId(){}
	
	public SaleId(String id) {this.saleId = id;}
	
	public String toString() {return this.saleId;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		SaleId e = (SaleId) o;
		return saleId.equals(e.saleId);
	}

	public String getValue(){
		return this.saleId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(saleId);
	}
}
