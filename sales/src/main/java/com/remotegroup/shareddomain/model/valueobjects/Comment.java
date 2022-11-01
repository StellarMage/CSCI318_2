package com.remotegroup.shareddomain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Comment implements Serializable{

	private String comment;
	
	Comment(){}
	
	public Comment(String id) {this.comment = id;}
	
	public String toString() {return this.comment;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		Comment e = (Comment) o;
		return comment.equals(e.comment);
	}

	public String getValue(){
		return comment;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(comment);
	}
}