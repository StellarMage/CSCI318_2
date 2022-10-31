package com.remotegroup.shareddomain.model.aggregates;

import com.remotegroup.shareddomain.model.commands.CreateOnlineSaleCommand;
import com.remotegroup.shareddomain.model.commands.UpdateOnlineSaleCommand;
import com.remotegroup.shareddomain.model.valueobjects.Address;
import com.remotegroup.shareddomain.model.valueobjects.CustomerName;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OnlineSale extends Sale {
    private @Id @GeneratedValue Long id;

    @Embedded
    CustomerName customerName;
    @Embedded
    Address address;

    public OnlineSale () {
    	super();
    }

    public OnlineSale(CreateOnlineSaleCommand command){
        this.saleId = new SaleId(command.getSaleId());
        this.customerName = new CustomerName(command.getCustomerName());
        this.address = new Address(command.getAddress());
    }

    public OnlineSale updateOnlineSale(UpdateOnlineSaleCommand command) {
        this.saleId = new SaleId(command.getSaleId());
        this.customerName = new CustomerName(command.getCustomerName());
        this.address = new Address(command.getAddress());
        return this;
    }

    public Long getId(){
        return this.id;
    }



    public CustomerName getCustomerName(){
        return this.customerName;
    }

    public Address getAddress(){
        return this.address;
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
        return Objects.equals(this.customerName, OnlineSale.customerName)
        && Objects.equals(this.address, OnlineSale.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.customerName, this.address);
    }

    @Override
    public String toString() {
        return super.toString() +"OnlineSale{"
        + " CustomerName id='" + this.customerName.getValue() + '\'' 
        + ", address='" + this.address.getValue() + '\''
         + '}';
    }
}