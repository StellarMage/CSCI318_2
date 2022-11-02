package com.remotegroup.shareddomain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class Name implements Serializable{

	private String name;
	
	Name(){}
	
	public Name(@JsonProperty("name") String id) {this.name = id;}
	
	public String toString() {return this.name;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		Name e = (Name) o;
		return name.equals(e.name);
	}

	public String getValue(){
		return name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
