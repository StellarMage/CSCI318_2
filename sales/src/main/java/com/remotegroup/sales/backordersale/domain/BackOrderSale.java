package com.remotegroup.sales.backordersale.domain;

import com.remotegroup.sales.sale.domain.*;
import com.remotegroup.sales.shareddomain.BusinessIntelligence;

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

	public BackOrderSale(Long p, String pn, Integer q, String dt, String ph, double pP){
		super();

		this.productPrice = pP;
    	this.itemId = p;
    	this.itemName = pn;
    	this.quantity = q;
    	this.DataTime = dt;
		phoneNumber = ph;

		BusinessIntelligence bI = (new BusinessIntelligence(pn, q, pP));
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String p){
		this.phoneNumber = p;
	}
	
}
