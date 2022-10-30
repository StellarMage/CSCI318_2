package com.remotegroup.shareddomain.model.commands;

public class CreateOnlineSaleCommand extends CreateSaleCommand{

	private String onlineSaleId;
	private String saleId;
	private String customerName;
	private String address;
	
	public CreateOnlineSaleCommand(String saleId, String customerName, String address) {
		super();
		this.saleId = saleId;
		this.customerName = customerName;
		this.address = address;
	}

	public String getOnlineSaleId() {
		return onlineSaleId;
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

	public String setOnlineSaleId() {
		return onlineSaleId;
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
