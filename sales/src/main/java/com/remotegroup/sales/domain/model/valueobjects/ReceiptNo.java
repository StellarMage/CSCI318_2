package com.remotegroup.sales.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class ReceiptNo implements Serializable{

	private String receiptNo;
	
	public ReceiptNo() {}
	
	public ReceiptNo(String receiptNo) {this.receiptNo = receiptNo;}
	
	public String toString() {return this.receiptNo;}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o== null || this.getClass() != o.getClass()) return false;
		ReceiptNo e = (ReceiptNo) o;
		return receiptNo.equals(e.receiptNo);
	}

	public String getValue(){
		return receiptNo;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(receiptNo);
	}
}
