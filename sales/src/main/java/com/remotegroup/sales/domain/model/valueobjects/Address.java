package com.remotegroup.sales.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable{

	private String address;
	
	public Address() {}
	
	public Address(String address) {this.address = address;}
	
	public String toString() {return this.address;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		Address e = (Address) o;
		return address.equals(e.address);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(address);
	}
}