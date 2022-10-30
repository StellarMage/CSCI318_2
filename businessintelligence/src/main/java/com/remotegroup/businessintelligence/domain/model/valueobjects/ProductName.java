package com.remotegroup.businessintelligence.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class ProductName implements Serializable {

	private String productName;
	
	ProductName(){}
	
	public ProductName(String id) {this.productName = id;}
	
	public String toString() {return this.productName;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		ProductName e = (ProductName) o;
		return productName.equals(e.productName);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(productName);
	}
}