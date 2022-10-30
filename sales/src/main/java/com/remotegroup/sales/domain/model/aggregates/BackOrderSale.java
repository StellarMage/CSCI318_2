package com.remotegroup.sales.domain.model.aggregates;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.remotegroup.sales.domain.model.commands.CreateBackOrderSaleCommand;
import com.remotegroup.sales.domain.model.commands.UpdateBackOrderSaleCommand;
import com.remotegroup.sales.domain.model.valueobjects.PhoneNumber;

@Entity
public class BackOrderSale extends Sale {
	
	private @Id @GeneratedValue Long id;

	@Embedded
	BackOrderSaleId backOrderSaleId;
	@Embedded
	PhoneNumber phoneNumber;
	
	BackOrderSale(){
		super();
	}

	public BackOrderSale(CreateBackOrderSaleCommand command){
        this.backOrderSaleId = new BackOrderSaleId(command.getBackOrderSaleId());
        this.saleId = new SaleId(command.getSaleId());
        this.phoneNumber = new PhoneNumber(command.getPhoneNumber());
    }

    public BackOrderSale updateBackOrderSale(UpdateBackOrderSaleCommand command) {
        this.backOrderSaleId = new BackOrderSaleId(command.getBackOrderSaleId());
        this.saleId = new SaleId(command.getSaleId());
        this.phoneNumber = new PhoneNumber(command.getPhoneNumber());
        return this;
    }

	public Long getId() {
		return this.id;
	}

	public BackOrderSaleId getBackOrderSaleId() {
		return this.backOrderSaleId;
	}
	
	public PhoneNumber getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setBackOrderSaleId(BackOrderSaleId b){
		this.backOrderSaleId = b;
	}

	public void setPhoneNumber(PhoneNumber p){
		this.phoneNumber = p;
	}
	
}
