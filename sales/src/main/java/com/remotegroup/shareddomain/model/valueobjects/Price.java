package com.remotegroup.shareddomain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class Price implements Serializable{

	private String price;
	
	Price(){}
	
	public Price(@JsonProperty("price") String id) {this.price = id;}
	
	public String toString() {return this.price;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		Price e = (Price) o;
		return price.equals(e.price);
	}

	public String getValue(){
		return price;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(price);
	}
}