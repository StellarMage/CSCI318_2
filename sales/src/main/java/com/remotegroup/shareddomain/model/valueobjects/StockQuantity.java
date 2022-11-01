package com.remotegroup.shareddomain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class StockQuantity implements Serializable{

	private int stockQuantity;
	
	StockQuantity(){}
	
	public StockQuantity(int id) {this.stockQuantity = id;}
	
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