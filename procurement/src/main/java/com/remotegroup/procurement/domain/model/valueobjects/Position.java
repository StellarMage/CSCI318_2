package com.remotegroup.procurement.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Position implements Serializable {

	private String position;
	
	Position(){}
	
	public Position(String p) {this.position = p;}
	
	public String toString() {return this.position;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		Position e = (Position) o;
		return position.equals(e.position);
	}

	public String getValue(){
		return this.position;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(position);
	}
}
