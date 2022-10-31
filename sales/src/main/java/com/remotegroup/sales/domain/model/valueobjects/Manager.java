package com.remotegroup.sales.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Manager implements Serializable{

	private String manager;
	
	public Manager() {}
	
	public Manager(String manager) {this.manager = manager;}
	
	public String toString() {return this.manager;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		Manager e = (Manager) o;
		return manager.equals(e.manager);
	}

	public String getValue(){
		return manager;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(manager);
	}
}