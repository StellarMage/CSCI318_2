package com.remotegroup.shareddomain.model.aggregates;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

//aggregate id 
@Embeddable
public class InStoreSaleId implements Serializable{

	private String instoresaleId;
	
	InStoreSaleId(){}
	
	public InStoreSaleId(String id) {this.instoresaleId = id;}
	
	public String toString() {return this.instoresaleId;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		InStoreSaleId e = (InStoreSaleId) o;
		return instoresaleId.equals(e.instoresaleId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(instoresaleId);
	}
}