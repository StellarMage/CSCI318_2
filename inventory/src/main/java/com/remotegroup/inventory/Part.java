package com.remotegroup.inventory;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Part {
    private @Id @GeneratedValue Long id;
    Long supplierId;
    String name;
    String description;
    int stockQuantity;

    Part () {}

    public Part(Long i, String n, String d, int s){
        supplierId = i;
        name = n;
        description = d;
        stockQuantity = s;
    }
    public Long getId(){
        return this.id;
    }

    public Long getSupplierId(){
        return this.supplierId;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getStockQuantity() {
    	return this.stockQuantity;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setSupplierId(Long sId) {
        this.supplierId = sId;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setDescription(String newDescription){
        this.description = newDescription;
    }
    
    public void setStockQuantity(int s) {
    	this.stockQuantity = s;
    }

    public boolean equals(Part o) {
        if (this == o)
            return true;
        if (!(o instanceof Part))
            return false;
            Part part = (Part) o;
        return Objects.equals(this.id, part.id) 
        && Objects.equals(this.supplierId, part.supplierId)
        && Objects.equals(this.name, part.name)
        && Objects.equals(this.description, part.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.supplierId, this.name, this.description);
    }

    @Override
    public String toString() {
        return "Part{" + "id=" + this.id + '\''
        + ", supplier id='" + this.supplierId + '\'' 
        + ", name='" + this.name + '\''
         + ", description='" + this.description + '\''
         + '}';
    }
}