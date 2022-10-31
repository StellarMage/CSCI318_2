package com.remotegroup.shareddomain.model.commands;

public class UpdateSaleCommand {
	
	protected String saleId;
	protected String itemId;
	protected String itemName;
	protected String quantity;
	protected String dataTime;
	protected String productPrice;
	
	public UpdateSaleCommand(String saleId, String itemId, String itemName, String quantity, String dataTime, String productPrice) {
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

	public String getQuantity() {
		return quantity;
	}

	public String getDataTime() {
		return dataTime;
	}

	public String getProductPrice() {
		return productPrice;
	}

}
