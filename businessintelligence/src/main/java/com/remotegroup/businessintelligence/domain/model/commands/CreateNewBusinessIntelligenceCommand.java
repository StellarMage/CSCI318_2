package com.remotegroup.businessintelligence.domain.model.commands;

public class CreateNewBusinessIntelligenceCommand {

	private String businessIntelligenceId;
	private String productName;
	private Integer quantity;
	private Double price;
	private Double total;
	
	public CreateNewBusinessIntelligenceCommand(String productName, Integer quantity, Double price, Double total) {
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.total = quantity * price;
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
