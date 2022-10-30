package com.remotegroup.sales.domain.model.commands;

public class CreateBackOrderSaleCommand extends CreateSaleCommand{


	private String phoneNumber;

	
	public CreateBackOrderSaleCommand( String itemName, String quantity, String dataTime, String productPrice, String itemId, String phoneNumber) {
		super(itemId, itemName, quantity, dataTime, productPrice);
		this.phoneNumber = phoneNumber;
		this.itemId = itemId;
	}
	
	

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getSaleId() {
		return saleId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
