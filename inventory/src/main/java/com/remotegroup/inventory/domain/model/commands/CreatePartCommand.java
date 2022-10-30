package com.remotegroup.inventory.domain.model.commands;

public class CreatePartCommand {

    private String partId;
    private String supplierId;
    private String name;
    private String description;
    private String stockQuantity;
    
    public CreatePartCommand(String supplierId, String name, String description, String stockQuantity) {
		this.supplierId = supplierId;
		this.name = name;
        this.description = description;
        this.stockQuantity = stockQuantity;
	}
	
    public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    public String getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(String stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
}
