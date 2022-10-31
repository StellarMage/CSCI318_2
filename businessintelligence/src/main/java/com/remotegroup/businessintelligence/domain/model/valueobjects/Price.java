package com.remotegroup.businessintelligence.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Price implements Serializable {

	private Double price;
	
	Price(){}
	
	public Price(Double id) {this.price = id;}
	
	public Double toDouble() {return this.price;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		Price e = (Price) o;
		return price.equals(e.price);
	}

	public Double getValue(){
		return price;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(price);
	}
}