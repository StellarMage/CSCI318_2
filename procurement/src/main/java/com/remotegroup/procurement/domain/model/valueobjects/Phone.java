package com.remotegroup.procurement.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Phone implements Serializable {

	private String phone;
	
	Phone(){}
	
	public Phone(String phone) {this.phone = phone;}
	
	public String toString() {return this.phone;}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		Phone e = (Phone) o;
		return phone.equals(e.phone);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(phone);
	}
}
