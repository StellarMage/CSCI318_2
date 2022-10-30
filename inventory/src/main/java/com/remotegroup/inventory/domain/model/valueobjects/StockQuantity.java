package com.remotegroup.inventory.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class StockQuantity implements Serializable{

	private String stockQuantity;
	
	StockQuantity(){}
	
	public StockQuantity(String id) {this.stockQuantity = id;}
	
	public String toString() {return this.stockQuantity;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		StockQuantity e = (StockQuantity) o;
		return stockQuantity.equals(e.stockQuantity);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(stockQuantity);
	}
}