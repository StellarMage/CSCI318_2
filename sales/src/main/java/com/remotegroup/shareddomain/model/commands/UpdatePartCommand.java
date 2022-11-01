package com.remotegroup.shareddomain.model.commands;

public class UpdatePartCommand {
    
    private String partId;
    private String supplierId;
    private String name;
    private String description;
    private int stockQuantity;
    
    public UpdatePartCommand(String supplierId, String name, String description, int stockQuantity) {
		this.supplierId = supplierId;
		this.name = name;
        this.description = description;
        this.stockQuantity = stockQuantity;
	}
	
    public String getPartId() {
		return partId;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
    public int getStockQuantity() {
		return stockQuantity;
	}
}
