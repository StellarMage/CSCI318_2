package com.remotegroup.sales.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Quantity implements Serializable{

	private String quantity;
	
	public Quantity() {}
	
	public Quantity(String quantity) {this.quantity = quantity;}
	
	public String toString() {return this.quantity;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		Quantity e = (Quantity) o;
		return quantity.equals(e.quantity);
	}

	public String getValue(){
		return this.quantity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(quantity);
	}
}