package com.remotegroup.shareddomain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BusinessIntelligence {
    private @Id @GeneratedValue Long id;
    String productName;
    int quantity;
    double price;
    double total;

    BusinessIntelligence () {}

    public BusinessIntelligence(String n, int q, double p){
        productName = n;
        quantity = q;
        price = p;
        total = q * p;
    }

    public BusinessIntelligence(Sale s){
        productName = s.getItemName();
        quantity = s.getQuantity();
        price = s.getProductPrice();
        total = quantity * price;
    }

    public Long getId(){
        return this.id;
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
        this.id = id;
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
            BusinessIntelligence businessIntillgence = (BusinessIntelligence) o;
        return Objects.equals(this.id, businessIntillgence.id) 
        && Objects.equals(this.productName, businessIntillgence.productName)
        && Objects.equals(this.quantity, businessIntillgence.quantity)
        && Objects.equals(this.price, businessIntillgence.price)
        && Objects.equals(this.total, businessIntillgence.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.productName, this.quantity, this.price, this.total);
    }

    @Override
    public String toString() {
        return "BusinessIntillgence{" + "id=" + this.id + '\''
        + ", productName='" + this.productName + '\'' 
        + ", quantity='" + this.quantity + '\'' 
        + ", price='" + this.price + '\''
         + ", total='" + this.total + '\''
         + '}';
    }
}