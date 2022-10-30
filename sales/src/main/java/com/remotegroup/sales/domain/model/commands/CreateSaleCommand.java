package com.remotegroup.sales.domain.model.commands;

public class CreateSaleCommand {

	private String saleId;
	private String itemId;
	private String itemName;
	private String quantity;
	private String dataTime;
	private String productPrice;

	public CreateSaleCommand() {}
	
	public CreateSaleCommand(String itemId, String itemName, String quantity, String dataTime, String productPrice) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.dataTime = dataTime;
		this.productPrice = productPrice;
	}

	public String getSaleId() {
		return saleId;
	}

	public String getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getDataTime() {
		return dataTime;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	
	
	
}