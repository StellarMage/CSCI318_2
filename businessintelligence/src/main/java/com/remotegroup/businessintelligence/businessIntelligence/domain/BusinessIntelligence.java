package com.remotegroup.businessintelligence.businessIntelligence.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.remotegroup.businessintelligence.shareddomain.Sale;

@Entity
public class BusinessIntelligence {
    @Id Long saleId;
    String productName;
    int quantity;
    double price;
    double total;

    BusinessIntelligence () {}

    public BusinessIntelligence(Long i, String n, int q, double p){
        saleId = i;
        productName = n;
        quantity = q;
        price = p;
        total = q * p;
    }

    public BusinessIntelligence(Sale s){
        saleId = s.getId();
        productName = s.getItemName();
        quantity = s.getQuantity();
        price = s.getProductPrice();
        total = quantity * price;
    }

    
    public Long getId(){
        return this.saleId;
    }

    public String getName(){
        return this.productName;
    }

    public int getQuantity() {
    	return this.quantity;
    }

    public double getPrice() {
    	return this.price;
    }

    public double getTotal() {
    	return this.total;
    }
    
    public void setId(Long id) {
        this.saleId = id;
    }

    public void setName(String n){
        this.productName = n;
    }
    
    public void setQuantity(int q) {
    	this.quantity = q;
        this.total = this.price * this.quantity;
    }

    public void setPrice(double p) {
    	this.price = p;
        this.total = this.price * this.quantity;
    }

    public boolean equals(BusinessIntelligence o) {
        if (this == o)
            return true;
        if (!(o instanceof BusinessIntelligence))
            return false;
            BusinessIntelligence businessIntelligence = (BusinessIntelligence) o;
        return Objects.equals(this.saleId, businessIntelligence.saleId) 
        && Objects.equals(this.productName, businessIntelligence.productName)
        && Objects.equals(this.quantity, businessIntelligence.quantity)
        && Objects.equals(this.price, businessIntelligence.price)
        && Objects.equals(this.total, businessIntelligence.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.saleId, this.productName, this.quantity, this.price, this.total);
    }

    @Override
    public String toString() {
        return "businessIntelligence{" + "id=" + this.saleId + '\''
        + ", productName='" + this.productName + '\'' 
        + ", quantity='" + this.quantity + '\'' 
        + ", price='" + this.price + '\''
         + ", total='" + this.total + '\''
         + '}';
    }
}