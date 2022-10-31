package com.remotegroup.shareddomain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class ItemId implements Serializable{

	private String itemId;
	
	public ItemId() {}
	
	public ItemId(String itemId) {this.itemId = itemId;}
	
	public String toString() {return this.itemId;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		ItemId e = (ItemId) o;
		return itemId.equals(e.itemId);
	}

	public String getValue(){
		return this.itemId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(itemId);
	}
}