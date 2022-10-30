package com.remotegroup.businessintelligence.domain.model.commands;

public class UpdateBusinessIntelligenceCommand {

	private String businessIntelligenceId;
	private String productName;
	private Integer quantity;
	private Double price;
	private Double total;
	
	public UpdateBusinessIntelligenceCommand(String businessIntelligenceId, String productName, Integer quantity, Double price, Double total) {
		this.businessIntelligenceId = businessIntelligenceId;
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
}
