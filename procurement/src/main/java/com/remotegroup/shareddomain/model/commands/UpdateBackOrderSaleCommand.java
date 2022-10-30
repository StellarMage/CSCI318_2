package com.remotegroup.shareddomain.model.commands;

public class UpdateBackOrderSaleCommand {
	
	private String backOrderSaleId;
	private String saleId;
	private String phoneNumber;
	
	public UpdateBackOrderSaleCommand(String backOrderSaleId, String saleId, String phoneNumber) {
		this.backOrderSaleId = backOrderSaleId;
		this.saleId = saleId;
		this.phoneNumber = phoneNumber;
	}

	public String getBackOrderSaleId() {
		return backOrderSaleId;
	}

	public String getSaleId() {
		return saleId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
}
