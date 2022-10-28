package com.remotegroup.sales.onlinesale.domain;

import com.remotegroup.sales.sale.domain.*;
import com.remotegroup.sales.shareddomain.BusinessIntelligence;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OnlineSale extends Sale {
    private @Id @GeneratedValue Long id;
    String customerName;
    String address;

    OnlineSale () {
    	super();
    }

    public OnlineSale(Long p, String pn, Integer q, String dt, String c, String n, double pP){
    	super();
    	this.itemId = p;
    	this.itemName = pn;
    	this.quantity = q;
    	this.dataTime = dt;
        this.productPrice = pP;
        customerName = c;
        address = n;

        BusinessIntelligence bI = (new BusinessIntelligence(pn, q, pP));
    }

    public String getCustomerName(){
        return this.customerName;
    }

    public String getAddress(){
        return address;
    }

    public void setCustomerName(String newCustomerName) {
        this.customerName = newCustomerName;
    }

    public void setAddress(String newAddress){
        this.address = newAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof OnlineSale))
            return false;
            OnlineSale OnlineSale = (OnlineSale) o;
        return Objects.equals(this.id, OnlineSale.id) 
        && Objects.equals(this.customerName, OnlineSale.customerName)
        && Objects.equals(this.address, OnlineSale.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.customerName, this.address);
    }

    @Override
    public String toString() {
        return "OnlineSale{" + "id=" + this.id + '\''
        + ", CustomerName id='" + this.customerName + '\'' 
        + ", address='" + this.address + '\''
         + '}';
    }
}