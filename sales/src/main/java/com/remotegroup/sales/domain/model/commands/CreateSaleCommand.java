package com.remotegroup.sales.domain.model.commands;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateSaleCommand {

	protected String saleId;
	protected String itemId;
	protected String itemName;
	protected String quantity;
	protected String dataTime;
	protected String productPrice;

	public CreateSaleCommand() {}
	
	public CreateSaleCommand(@JsonProperty("productId") String itemId,@JsonProperty("name") String itemName,@JsonProperty("quantity") String quantity,@JsonProperty("dataTime") String dataTime,@JsonProperty("price") String productPrice) {
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
