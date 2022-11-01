package com.remotegroup.sales.shareddomain.events;

public class SaleEventData {
	String itemName;
	int quantity;
	double productPrice;
	
	SaleEventData(){}
	
	SaleEventData(String itemName, int quantity, double productPrice){
		this.itemName = itemName;
		this.quantity = quantity;
		this.productPrice = productPrice;
	}

	public String getItemName() {
		return itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	
}
