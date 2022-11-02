package com.remotegroup.shareddomain.model.aggregates;

import java.util.Objects;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class ProductId {
    
    private String productId;
	
	ProductId(){}
	
	public ProductId(@JsonProperty("value") String id) {this.productId = id;}
	
	public String toString() {return this.productId;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		ProductId e = (ProductId) o;
		return productId.equals(e.productId);
	}
	
	public String getValue(){
		return productId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId);
	}
}
