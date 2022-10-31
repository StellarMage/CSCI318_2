package com.remotegroup.shareddomain.model.commands;

public class UpdateOnlineSaleCommand extends UpdateSaleCommand{
	
	private String customerName;
	private String address;
	
	public UpdateOnlineSaleCommand(String saleId, String itemId, String itemName, String quantity, String dataTime, String productPrice, String customerName, String address) {
		super(saleId, itemId, itemName, quantity, dataTime, productPrice);
		this.customerName = customerName;
		this.address = address;
	}


	public String getCustomerName() {
		return customerName;
	}

	public String getAddress() {
		return address;
	}
}
