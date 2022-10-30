package com.remotegroup.shareddomain.model.commands;

public class UpdateSaleCommand {
	
	private String saleId;
	private String itemId;
	private String itemName;
	private Integer quantity;
	private String dataTime;
	private Double productPrice;
	
	public UpdateSaleCommand(String saleId, String itemId, String itemName, Integer quantity, String dataTime, Double productPrice) {
		this.saleId = saleId;
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

}
