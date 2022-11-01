package com.remotegroup.businessintelligence.domain.model.commands;

import com.remotegroup.shareddomain.events.SaleEvent;
import com.remotegroup.shareddomain.events.SaleEventData;

public class CreateSaleBusinessIntelligenceCommand {

	private String businessIntelligenceId;
	private String productName;
	private Integer quantity;
	private Double price;
	private Double total;
	
	public CreateSaleBusinessIntelligenceCommand(SaleEvent s) {
		SaleEventData d = s.getData();
		this.productName = d.getItemName();
		this.quantity = d.getQuantity();
		this.price = d.getProductPrice();
		this.total = this.quantity * this.price;
	}

	public String getBusinessIntelligenceId() {
		return businessIntelligenceId;
	}

	public String getProductName() {
		return productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Double getPrice() {
		return price;
	}

	public Double getTotal() {
		return total;
	}

	public void setBusinessIntelligenceId(String businessIntelligenceId) {
		this.businessIntelligenceId = businessIntelligenceId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setQuantity(String quantity) {
		this.quantity = Integer.parseInt(quantity);
		this.total = this.quantity * this.price;
	}

	public void setPrice(String price) {
		this.price = Double.parseDouble(price);
		this.total = this.quantity * this.price;
	}
}
