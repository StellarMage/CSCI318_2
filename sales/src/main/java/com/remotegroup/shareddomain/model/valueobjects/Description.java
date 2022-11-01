package com.remotegroup.shareddomain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Description implements Serializable{

	private String description;
	
	Description(){}
	
	public Description(String id) {this.description = id;}
	
	public String toString() {return this.description;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		Description e = (Description) o;
		return description.equals(e.description);
	}
	
	public String getValue(){
		return description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description);
	}
}