package com.remotegroup.shareddomain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Quantity implements Serializable {

	private Integer quantity;
	
	Quantity(){}
	
	public Quantity(Integer id) {this.quantity = id;}
	
	public Integer toInteger() {return this.quantity;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		Quantity e = (Quantity) o;
		return quantity.equals(e.quantity);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(quantity);
	}
}