package com.remotegroup.shareddomain.model.commands;

public class UpdateBackOrderSaleCommand extends UpdateSaleCommand {
	
	private String phoneNumber;
	
	public UpdateBackOrderSaleCommand(String saleId, String itemId, String itemName, String quantity, String dataTime, String productPrice, String phoneNumber) {
		super(saleId, itemId, itemName, quantity, dataTime, productPrice);
		this.phoneNumber = phoneNumber;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}
}
