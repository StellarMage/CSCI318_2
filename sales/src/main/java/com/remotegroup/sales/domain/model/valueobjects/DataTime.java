package com.remotegroup.sales.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class DataTime implements Serializable{

	private String dataTime;
	
	public DataTime() {}
	
	public DataTime(String dataTime) {this.dataTime = dataTime;}
	
	public String toString() {return this.dataTime;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		DataTime e = (DataTime) o;
		return dataTime.equals(e.dataTime);
	}

	public String getValue(){
		return this.dataTime;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dataTime);
	}
}