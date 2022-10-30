package com.remotegroup.shareddomain.model.aggregates;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

//aggregate id 
@Embeddable
public class BackOrderSaleId implements Serializable{

	private String backordersaleId;
	
	BackOrderSaleId(){}
	
	public BackOrderSaleId(String id) {this.backordersaleId = id;}
	
	public String toString() {return this.backordersaleId;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		BackOrderSaleId e = (BackOrderSaleId) o;
		return backordersaleId.equals(e.backordersaleId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(backordersaleId);
	}
}