package com.remotegroup.shareddomain.model.commands;

public class CreateSaleCommand {

	private String saleId;
	private String itemId;
	private String itemName;
	private Integer quantity;
	private String dataTime;
	private Double productPrice;

	public CreateSaleCommand() {}
	
	public CreateSaleCommand(String itemId, String itemName, Integer quantity, String dataTime, Double productPrice) {
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

	public Integer getQuantity() {
		return quantity;
	}

	public String getDataTime() {
		return dataTime;
	}

	public Double getProductPrice() {
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

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	
	
	
}
