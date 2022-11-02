package com.remotegroup.shareddomain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class StockQuantity implements Serializable{

	@JsonProperty("value")
	private int stockQuantity;
	
	StockQuantity(){}
	
	public StockQuantity(@JsonProperty("value") int id) {this.stockQuantity = id;}
	
	public int get() {return this.stockQuantity;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		StockQuantity e = (StockQuantity) o;
		return stockQuantity == e.stockQuantity;
	}

	public int getValue(){
		return stockQuantity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(stockQuantity);
	}
}