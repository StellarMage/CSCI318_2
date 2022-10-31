package com.remotegroup.procurement.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class CompanyName implements Serializable{

	private String name;
	
	CompanyName(){}
	
	public CompanyName(String name) {this.name = name;}
	
	public String toString() {return this.name;}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		CompanyName e = (CompanyName) o;
		return name.equals(e.name);
	}
	
	public String getValue(){
		return this.name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
