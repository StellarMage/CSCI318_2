package com.remotegroup.businessintelligence.domain.model.commands;

import com.remotegroup.shareddomain.model.aggregates.Sale;

public class CreateSaleBusinessIntelligenceCommand {

	private String businessIntelligenceId;
	private String productName;
	private Integer quantity;
	private Double price;
	private Double total;
	
	public CreateSaleBusinessIntelligenceCommand(Sale s) {
		this.productName = s.itemName.toString();
		this.quantity = Integer.parseInt(s.quantity.toString());
		this.price = Double.parseDouble(s.productPrice.toString());
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
