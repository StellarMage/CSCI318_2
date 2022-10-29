package com.remotegroup.inventory.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class ComprisingParts implements Serializable{

	private String comprisingParts;
	
	ComprisingParts(){}
	
	public ComprisingParts(String id) {this.comprisingParts = id;}
	
	public String toString() {return this.comprisingParts;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		ComprisingParts e = (ComprisingParts) o;
		return comprisingParts.equals(e.comprisingParts);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(comprisingParts);
	}
}
