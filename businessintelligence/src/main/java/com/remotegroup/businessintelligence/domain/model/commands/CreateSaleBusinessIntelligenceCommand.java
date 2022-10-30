package com.remotegroup.businessintelligence.domain.model.commands;

import com.remotegroup.shareddomain.model.aggregates.Sale;

public class CreateSaleBusinessIntelligenceCommand {

	private String businessIntelligenceId;
	private String productName;
	private Integer quantity;
	private Double price;
	private Double total;
	
	public CreateSaleBusinessIntelligenceCommand(Sale s) {
		this.productName = s.itemId.toString();
		this.quantity = s.quantity.toInteger();
		this.price = s.productPrice.toDouble();
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

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
		this.total = this.quantity * this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
		this.total = this.quantity * this.price;
	}
}
