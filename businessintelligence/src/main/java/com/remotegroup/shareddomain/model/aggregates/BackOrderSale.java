package com.remotegroup.shareddomain.model.aggregates;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.remotegroup.shareddomain.model.commands.CreateBackOrderSaleCommand;
import com.remotegroup.shareddomain.model.commands.UpdateBackOrderSaleCommand;
import com.remotegroup.shareddomain.model.valueobjects.PhoneNumber;

@Entity
public class BackOrderSale extends Sale {
	
	private @Id @GeneratedValue Long id;

	@Embedded
	PhoneNumber phoneNumber;
	
	public BackOrderSale(){
		super();
	}

	public BackOrderSale(CreateBackOrderSaleCommand command){
        this.saleId = new SaleId(command.getSaleId());
        this.phoneNumber = new PhoneNumber(command.getPhoneNumber());
    }

    public BackOrderSale updateBackOrderSale(UpdateBackOrderSaleCommand command) {
        this.saleId = new SaleId(command.getSaleId());
        this.phoneNumber = new PhoneNumber(command.getPhoneNumber());
        return this;
    }

	public Long getId() {
		return this.id;
	}
	
	public PhoneNumber getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(PhoneNumber p){
		this.phoneNumber = p;
	}
	
}
