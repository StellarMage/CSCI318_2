package com.remotegroup.shareddomain.model.aggregates;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

//aggregate id 
@Embeddable
public class StoreId implements Serializable{

	private String storeId;
	
	StoreId(){}
	
	public StoreId(String id) {this.storeId = id;}
	
	public String toString() {return this.storeId;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		StoreId e = (StoreId) o;
		return storeId.equals(e.storeId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(storeId);
	}
}