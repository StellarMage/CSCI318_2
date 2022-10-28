package com.remotegroup.sales.instoresale.domain;

import com.remotegroup.sales.sale.domain.*;
import com.remotegroup.sales.shareddomain.BusinessIntelligence;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InStoreSale extends Sale {
    private @Id @GeneratedValue Long id;
    Long storeId;
    String receiptNo;

    InStoreSale () {
    	super();
    }

    public InStoreSale(Long p, String pn, Integer q, String dt, Long i, String n, double pP){
    	super();
    	this.itemId = p;
    	this.itemName = pn;
    	this.quantity = q;
    	this.dataTime = dt;
        this.productPrice = pP;
        storeId = i;
        receiptNo = n;

        BusinessIntelligence bI = (new BusinessIntelligence(pn, q, pP));
    }

    public Long getStoreId(){
        return this.storeId;
    }

    public String getReceiptNo(){
        return receiptNo;
    }

    public void setStoreId(Long id) {
        this.storeId = id;
    }

    public void setReceipt(String newReceipt){
        this.receiptNo = newReceipt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof InStoreSale))
            return false;
            InStoreSale InStoreSale = (InStoreSale) o;
        return Objects.equals(this.id, InStoreSale.id) 
        && Objects.equals(this.storeId, InStoreSale.storeId)
        && Objects.equals(this.receiptNo, InStoreSale.receiptNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.storeId, this.receiptNo);
    }

    @Override
    public String toString() {
        return "InStoreSale{" + "id=" + this.id + '\''
        + ", store id='" + this.storeId + '\'' 
        + ", receipt='" + this.receiptNo + '\''
         + '}';
    }
}