package com.remotegroup.inventory.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class CompromisingParts implements Serializable{

	private String compromisingParts;
	
	CompromisingParts(){}
	
	public CompromisingParts(String id) {this.compromisingParts = id;}
	
	public String toString() {return this.compromisingParts;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		CompromisingParts e = (CompromisingParts) o;
		return compromisingParts.equals(e.compromisingParts);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(compromisingParts);
	}
}
