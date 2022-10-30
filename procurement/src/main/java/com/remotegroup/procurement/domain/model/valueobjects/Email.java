package com.remotegroup.procurement.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable 
public class Email implements Serializable {

	private String email;
	
	Email(){}
	
	public Email(String email) {
		try {
			this.email = validate(email);
		} catch (Exception e) {
			return;
		}
	}
	
	public String toString() {return this.email;}
	
	//validation
	private String validate(String email) throws Exception {
		if(isValid(email)) {
			return email;
		}else {
			throw new Exception("Email address is invalid");
		}
	}
	
	public boolean isValid(String email) {
		if(email.contains("@")) {
			return true;
		}else {return false;}
	}
	
	//equality 
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		Email e = (Email) o;
		return email.equals(e.email);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
	
}
