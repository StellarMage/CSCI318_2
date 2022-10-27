package com.remotegroup.inventory;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
    private @Id @GeneratedValue Long id;
    String name;
    double price;
    String comment;
    Long[][] comprisingParts; // [][0] = part_id, [][1] = parts_needed  NOTE: MUST be >= 1 Part with number >= 1
    int stockQuantity;
    
    Product () {}

    public Product(String n, double p, String c, Long[][] comprisingParts, int s){
        name = n;
        price = p;
        comment = c;
        this.comprisingParts = comprisingParts.clone();
        stockQuantity = s;
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public String getComment(){
        return comment;
    }
    
    public Long[][] getComprisingParts() {
    	return this.comprisingParts;
    }
    
    public int getStockQuantity() {
    	return this.stockQuantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setPrice(double newPrice){
        this.price = newPrice;
    }

    public void setComment(String newComment){
        this.comment = newComment;
    }
    
    public void setComprisingParts(Long[][] cp) {
    	this.comprisingParts = cp.clone();
    }
    
    public void setStockQuantity(int s) {
    	this.stockQuantity = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Product))
            return false;
            Product product = (Product) o;
        return Objects.equals(this.id, product.id) 
        && Objects.equals(this.name, product.name)
        && Objects.equals(this.price, product.price) 
        && Objects.equals(this.comment, product.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.price, this.comment);
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + this.id + '\''
        + ", name='" + this.name + '\''
         + ", price='" + String.valueOf(this.price) + '\''
         + ", comment='" + this.comment + '\'' 
         + '}';
    }
}