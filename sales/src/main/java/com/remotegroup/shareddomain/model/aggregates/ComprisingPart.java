package com.remotegroup.shareddomain.model.aggregates;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class ComprisingPart implements Serializable{
	
	@Embedded
	private PartId partid;
	private Long quantity;
	
	ComprisingPart(){}
	
	public ComprisingPart(PartId id, Long quantity){
		this.partid = id;
		this.quantity = quantity;
	}
	
	
	
	public PartId getPartId() {
		return partid;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setPartId(PartId partid) {
		this.partid = partid;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		ComprisingPart e = (ComprisingPart) o;
		return partid.equals(e.partid) && quantity == e.quantity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(partid, quantity);
	}

	@Override
	public String toString(){
		return "ComprisingPart{" + "partId=" + this.partid + '\''
        + ", quantity ='" + this.quantity + '\'' 
         + '}';
	}
}
