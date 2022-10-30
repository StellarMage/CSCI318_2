package com.remotegroup.procurement.domain.model.aggregates;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class SupplierId implements Serializable {

	private String supplierId;
	
	SupplierId(){}
	
	public SupplierId(String id) {this.supplierId = id;}
	
	public String toString() {return this.supplierId;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		SupplierId e = (SupplierId) o;
		return supplierId.equals(e.supplierId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(supplierId);
	}
}
