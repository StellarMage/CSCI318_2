package com.remotegroup.procurement.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Base implements Serializable{

	private String base;
	
	public Base() {}
	
	public Base(String base) {this.base = base;}
	
	public String toString() {return this.base;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		Base e = (Base) o;
		return base.equals(e.base);
	}
	
	public String getValue(){
		return this.base;
	}

	@Override
	public int hashCode() {
		return Objects.hash(base);
	}
}
