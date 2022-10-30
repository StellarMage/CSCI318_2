package com.remotegroup.sales.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class PhoneNumber implements Serializable{

	private String phoneNumber;
	
	public PhoneNumber() {}
	
	public PhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
	
	public String toString() {return this.phoneNumber;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		PhoneNumber e = (PhoneNumber) o;
		return phoneNumber.equals(e.phoneNumber);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(phoneNumber);
	}
}