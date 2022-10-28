package com.remotegroup.sharedomain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.remotegroup.businessintelligence.BusinessIntelligence;

@Entity
public class Sale {

    protected @Id @GeneratedValue Long id;
    Long itemId;
    String itemName;
    Integer quantity;
    String DataTime;
    double productPrice;

    Sale () {}

    public Sale(Long p, String pn, Integer q, String dt, double pP){
        itemId = p;
        itemName = pn;
        quantity = q;
        DataTime = dt;
        productPrice = pP;

        BusinessIntelligence bI = (new BusinessIntelligence(pn, q, pP));
    }
    public Long getId(){
        return this.id;
    }

    public Long getItemId(){
        return this.itemId;
    }

    public String getItemName(){
        return itemName;
    }

    public Integer getQuantity(){
        return quantity;
    }

    public String getDataTime(){
        return DataTime;
    }

    public double getProductPrice(){
        return productPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItemId(Long pId) {
        this.itemId = pId;
    }

    public void setItemName(String newProductName){
        this.itemName = newProductName;
    }

    public void setQuantity(Integer newQuantity){
        this.quantity = newQuantity;
    }

    public void setDataTime(String newDataTime){
        this.DataTime = newDataTime;
    }

    public void setProductPrice(double newProductPrice){
        this.productPrice = newProductPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Sale))
            return false;
            Sale sale = (Sale) o;
        return Objects.equals(this.id, sale.id) 
        && Objects.equals(this.itemId, sale.itemId)
        && Objects.equals(this.itemName, sale.itemName)
        && Objects.equals(this.quantity, sale.quantity)
        && Objects.equals(this.DataTime, sale.DataTime)
        && Objects.equals(this.productPrice, sale.productPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.itemId, this.itemName, this.quantity, this.productPrice);
    }

    @Override
    public String toString() {
        return "Sale{" + "id=" + this.id + '\''
        + ", product id='" + this.itemId + '\'' 
        + ", ProductName='" + this.itemName + '\''
        + ", quantity='" + this.quantity + '\''
        + ", DataTime='" + this.DataTime + '\''
        + ", ProductPrice='" + this.productPrice + '\''
        + '}';
    }
}
