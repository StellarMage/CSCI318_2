package com.remotegroup.shareddomain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class ProductPrice implements Serializable{

	private Double productPrice;
	
	public ProductPrice() {}
	
	public ProductPrice(Double productPrice) {this.productPrice = productPrice;}
	
	public Double toDouble() {return this.productPrice;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		ProductPrice e = (ProductPrice) o;
		return productPrice.equals(e.productPrice);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(productPrice);
	}
}