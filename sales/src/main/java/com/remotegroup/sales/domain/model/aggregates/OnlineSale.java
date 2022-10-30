package com.remotegroup.sales.domain.model.aggregates;

import com.remotegroup.sales.domain.model.commands.CreateOnlineSaleCommand;
import com.remotegroup.sales.domain.model.commands.UpdateOnlineSaleCommand;
import com.remotegroup.sales.domain.model.valueobjects.Address;
import com.remotegroup.sales.domain.model.valueobjects.CustomerName;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OnlineSale extends Sale {
    private @Id @GeneratedValue Long id;

    @Embedded
    OnlineSaleId onlineSaleId;
    @Embedded
    CustomerName customerName;
    @Embedded
    Address address;

    OnlineSale () {
    	super();
    }

    public OnlineSale(CreateOnlineSaleCommand command){
        this.onlineSaleId = new OnlineSaleId(command.getOnlineSaleId());
        this.saleId = new SaleId(command.getSaleId());
        this.customerName = new CustomerName(command.getCustomerName());
        this.address = new Address(command.getAddress());
    }

    public OnlineSale updateOnlineSale(UpdateOnlineSaleCommand command) {
        this.onlineSaleId = new OnlineSaleId(command.getOnlineSaleId());
        this.saleId = new SaleId(command.getSaleId());
        this.customerName = new CustomerName(command.getCustomerName());
        this.address = new Address(command.getAddress());
        return this;
    }

    public Long getId(){
        return this.id;
    }

    public OnlineSaleId getOnlineSaleId(){
        return this.onlineSaleId;
    }

    public CustomerName getCustomerName(){
        return this.customerName;
    }

    public Address getAddress(){
        return this.address;
    }

    public void setOnlineSaleId(OnlineSaleId newOnlineSaleId) {
        this.onlineSaleId = newOnlineSaleId;
    }

    public void setCustomerName(CustomerName newCustomerName) {
        this.customerName = newCustomerName;
    }

    public void setAddress(Address newAddress){
        this.address = newAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof OnlineSale))
            return false;
            OnlineSale OnlineSale = (OnlineSale) o;
        return Objects.equals(this.onlineSaleId, OnlineSale.onlineSaleId) 
        && Objects.equals(this.customerName, OnlineSale.customerName)
        && Objects.equals(this.address, OnlineSale.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.onlineSaleId, this.customerName, this.address);
    }

    @Override
    public String toString() {
        return "OnlineSale{" + "onlineSaleId=" + this.onlineSaleId + '\''
        + ", CustomerName id='" + this.customerName + '\'' 
        + ", address='" + this.address + '\''
         + '}';
    }
}