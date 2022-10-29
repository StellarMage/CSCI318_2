package com.remotegroup.inventory.domain.model.aggregates;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class ProductId {
    
    private String productId;
	
	ProductId(){}
	
	public ProductId(String id) {this.productId = id;}
	
	public String toString() {return this.productId;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		ProductId e = (ProductId) o;
		return productId.equals(e.productId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(productId);
	}
}
