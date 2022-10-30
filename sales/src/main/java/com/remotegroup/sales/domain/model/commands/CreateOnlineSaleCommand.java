package com.remotegroup.sales.domain.model.commands;

public class CreateOnlineSaleCommand extends CreateSaleCommand{

	private String customerName;
	private String address;
	
	public CreateOnlineSaleCommand(String itemId, String itemName, String quantity, String dataTime, String productPrice,String customerName, String address) {
		super(itemId, itemName, quantity, dataTime, productPrice);
		this.customerName = customerName;
		this.address = address;
	}


	public String getSaleId() {
		return saleId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getAddress() {
		return address;
	}


	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
