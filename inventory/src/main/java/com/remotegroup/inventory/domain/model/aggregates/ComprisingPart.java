package com.remotegroup.inventory.domain.model.aggregates;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class ComprisingPart implements Serializable{
	
	@Embedded
	private PartId part;
	private Long quantity;
	
	ComprisingPart(){}
	
	public ComprisingPart(PartId id, Long quantity){
		this.part = id;
		this.quantity = quantity;
	}
	
	
	
	public PartId getPart() {
		return part;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setPart(PartId part) {
		this.part = part;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		ComprisingPart e = (ComprisingPart) o;
		return part.equals(e.part) && quantity == e.quantity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(part, quantity);
	}
}
