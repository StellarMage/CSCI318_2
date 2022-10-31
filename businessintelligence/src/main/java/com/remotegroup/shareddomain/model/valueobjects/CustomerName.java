package com.remotegroup.shareddomain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerName implements Serializable{

	private String customerName;
	
	public CustomerName() {}
	
	public CustomerName(String customerName) {this.customerName = customerName;}
	
	public String toString() {return this.customerName;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		CustomerName e = (CustomerName) o;
		return customerName.equals(e.customerName);
	}

	public String getValue(){
		return this.customerName;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(customerName);
	}
}