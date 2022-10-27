package com.remotegroup.sales;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BackOrderSale extends Sale {
	private @Id @GeneratedValue Long id;
	String phoneNumber;
	
	BackOrderSale(){
		super();
	}

	public BackOrderSale(Long p, String pn, Integer q, String dt, String ph){
		super();

    	this.itemId = p;
    	this.itemName = pn;
    	this.quantity = q;
    	this.DataTime = dt;
		phoneNumber = ph;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String p){
		this.phoneNumber = p;
	}
	
}
