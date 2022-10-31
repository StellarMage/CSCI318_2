package com.remotegroup.sales.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class ProductPrice implements Serializable{

	private String productPrice;
	
	public ProductPrice() {}
	
	public ProductPrice(String productPrice) {this.productPrice = productPrice;}
	
	public String toString() {return this.productPrice;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		ProductPrice e = (ProductPrice) o;
		return productPrice.equals(e.productPrice);
	}

	public String getValue(){
		return this.productPrice;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(productPrice);
	}
}