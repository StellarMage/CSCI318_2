package com.remotegroup.inventory.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Name implements Serializable{

	private String name;
	
	Name(){}
	
	public Name(String id) {this.name = id;}
	
	public String toString() {return this.name;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		Name e = (Name) o;
		return name.equals(e.name);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
