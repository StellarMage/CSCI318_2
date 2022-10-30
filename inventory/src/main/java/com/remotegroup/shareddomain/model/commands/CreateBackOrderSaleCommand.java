package com.remotegroup.shareddomain.model.commands;

public class CreateBackOrderSaleCommand {

	private String backOrderSaleId;
	private String saleId;
	private String phoneNumber;
	
	public CreateBackOrderSaleCommand(String saleId, String phoneNumber) {
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

	public String setBackOrderSaleId() {
		return backOrderSaleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
