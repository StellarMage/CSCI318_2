package com.remotegroup.inventory.domain.model.aggregates;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class PartId implements Serializable{
    
    private String partId;

    PartId(){}

    public PartId(String id) {this.partId = id;}
	
	public String toString() {return this.partId;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		PartId e = (PartId) o;
		return partId.equals(e.partId);
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(partId);
	}
}
