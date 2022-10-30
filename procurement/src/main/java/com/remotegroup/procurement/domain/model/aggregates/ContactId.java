package com.remotegroup.procurement.domain.model.aggregates;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

//aggregate id 
@Embeddable
public class ContactId implements Serializable{

	private String contactId;
	
	ContactId(){}
	
	public ContactId(String id) {this.contactId = id;}
	
	public String toString() {return this.contactId;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		ContactId e = (ContactId) o;
		return contactId.equals(e.contactId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(contactId);
	}
}
