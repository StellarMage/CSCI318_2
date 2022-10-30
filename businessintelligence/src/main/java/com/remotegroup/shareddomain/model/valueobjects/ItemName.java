package com.remotegroup.shareddomain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class ItemName implements Serializable{

	private String itemName;
	
	public ItemName() {}
	
	public ItemName(String itemName) {this.itemName = itemName;}
	
	public String toString() {return this.itemName;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		ItemName e = (ItemName) o;
		return itemName.equals(e.itemName);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(itemName);
	}
}