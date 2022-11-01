package com.remotegroup.sales.domain.model.aggregates;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.domain.AbstractAggregateRoot;

import com.remotegroup.sales.domain.model.commands.CreateSaleCommand;
import com.remotegroup.sales.domain.model.commands.UpdateSaleCommand;
import com.remotegroup.sales.domain.model.valueobjects.DataTime;
import com.remotegroup.sales.domain.model.valueobjects.ItemId;
import com.remotegroup.sales.domain.model.valueobjects.ItemName;
import com.remotegroup.sales.domain.model.valueobjects.ProductPrice;
import com.remotegroup.sales.domain.model.valueobjects.Quantity;
import com.remotegroup.sales.shareddomain.events.SaleEvent;

@Entity
public class Sale extends AbstractAggregateRoot<Sale>{
    private @Id @GeneratedValue Long id;
    
    @Embedded
    public SaleId saleId;
    @Embedded
    public ItemId itemId;
    @Embedded
    public ItemName itemName;
    @Embedded
    public Quantity quantity;
    @Embedded
    public DataTime dataTime;
    @Embedded
    public ProductPrice productPrice;

    public Sale () {}

    public Sale(CreateSaleCommand command){
        this.saleId = new SaleId(command.getSaleId());
        this.itemId = new ItemId(command.getItemId());
        this.itemName = new ItemName(command.getItemName());
        this.quantity = new Quantity(command.getQuantity());
        this.dataTime = new DataTime(command.getDataTime());
        this.productPrice = new ProductPrice(command.getProductPrice());
        addDomainEvent(new SaleEvent(this));
    }

    public Sale updateSale(UpdateSaleCommand command) {
        this.saleId = new SaleId(command.getSaleId());
        this.itemId = new ItemId(command.getItemId());
        this.itemName = new ItemName(command.getItemName());
        this.quantity = new Quantity(command.getQuantity());
        this.dataTime = new DataTime(command.getDataTime());
        this.productPrice = new ProductPrice(command.getProductPrice());
        return this;
    }

    public Long getId(){
        return this.id;
    }

    public SaleId getSaleId(){
        return this.saleId;
    }

    public ItemId getItemId(){
        return this.itemId;
    }

    public ItemName getItemName(){
        return this.itemName;
    }

    public Quantity getQuantity(){
        return this.quantity;
    }

    public DataTime getDataTime(){
        return this.dataTime;
    }

    public ProductPrice getProductPrice(){
        return this.productPrice;
    }

    public void setSaleId(SaleId id) {
        this.saleId = id;
    }

    public void setItemId(ItemId pId) {
        this.itemId = pId;
    }

    public void setItemName(ItemName newProductName){
        this.itemName = newProductName;
    }

    public void setQuantity(Quantity newQuantity){
        this.quantity = newQuantity;
    }

    public void setDataTime(DataTime newDataTime){
        this.dataTime = newDataTime;
    }

    public void setProductPrice(ProductPrice newProductPrice){
        this.productPrice = newProductPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Sale))
            return false;
            Sale sale = (Sale) o;
        return Objects.equals(this.saleId, sale.saleId) 
        && Objects.equals(this.itemId, sale.itemId)
        && Objects.equals(this.itemName, sale.itemName)
        && Objects.equals(this.quantity, sale.quantity)
        && Objects.equals(this.dataTime, sale.dataTime)
        && Objects.equals(this.productPrice, sale.productPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.saleId, this.itemId, this.itemName, this.quantity, this.productPrice);
    }

    @Override
    public String toString() {
        return "Sale{" + "saleId=" + this.saleId.getValue() + '\''
        + ", product id='" + this.itemId.getValue() + '\'' 
        + ", ProductName='" + this.itemName.getValue() + '\''
        + ", quantity='" + this.quantity.getValue() + '\''
        + ", dataTime='" + this.dataTime.getValue() + '\''
        + ", ProductPrice='" + this.productPrice.getValue() + '\''
        + '}';
    }
    
    public void addDomainEvent(Object event) {
    	registerEvent(event);
    }
}
