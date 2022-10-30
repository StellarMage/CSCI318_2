package com.remotegroup.sales.domain.model.commands;

public class UpdateOnlineSaleCommand {
	
	private String onlineSaleId;
	private String saleId;
	private String customerName;
	private String address;
	
	public UpdateOnlineSaleCommand(String onlineSaleId, String saleId, String customerName, String address) {
		this.onlineSaleId = onlineSaleId;
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
}
