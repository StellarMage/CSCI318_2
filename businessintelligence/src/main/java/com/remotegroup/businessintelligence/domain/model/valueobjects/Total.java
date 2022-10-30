package com.remotegroup.businessintelligence.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Total implements Serializable {

	private Double total;
	
	Total(){}
	
	public Total(Double id) {this.total = id;}
	
	public Double toDouble() {return this.total;}

	public void calculate(Double price, Integer quantity){
		this.total = price * quantity;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		Total e = (Total) o;
		return total.equals(e.total);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(total);
	}
}