package com.remotegroup.businessintelligence;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BusinessIntillgence {
    private @Id @GeneratedValue Long id;
    int quantity;
    double price;
    double total;

    BusinessIntillgence () {}

    public BusinessIntillgence(int q, double p){
        quantity = q;
        price = p;
        total = p * q;
    }

    public Long getId(){
        return this.id;
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
    
    public void setQuantity(int q) {
    	this.quantity = q;
        this.total = this.price * this.quantity;
    }

    public void setPrice(int p) {
    	this.price = p;
        this.total = this.price * this.quantity;
    }

    public boolean equals(BusinessIntillgence o) {
        if (this == o)
            return true;
        if (!(o instanceof BusinessIntillgence))
            return false;
            BusinessIntillgence businessIntillgence = (BusinessIntillgence) o;
        return Objects.equals(this.id, businessIntillgence.id) 
        && Objects.equals(this.quantity, businessIntillgence.quantity)
        && Objects.equals(this.price, businessIntillgence.price)
        && Objects.equals(this.total, businessIntillgence.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.quantity, this.price, this.total);
    }

    @Override
    public String toString() {
        return "BusinessIntillgence{" + "id=" + this.id + '\''
        + ", quantity='" + this.quantity + '\'' 
        + ", price='" + this.price + '\''
         + ", total='" + this.total + '\''
         + '}';
    }
}