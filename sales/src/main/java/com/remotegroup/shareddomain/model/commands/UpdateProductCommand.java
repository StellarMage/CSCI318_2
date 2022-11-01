package com.remotegroup.shareddomain.model.commands;

public class UpdateProductCommand {
    
    private String productId;
    private String name;
    private String price;
    private String comment;
    private String[][] comprisingParts;
    private int stockQuantity;

    public UpdateProductCommand(String name, String price, String comment, String[][] comprisingParts, int stockQuantity) {
		this.name = name;
		this.price = price;
        this.comment = comment;
        this.comprisingParts = comprisingParts;
        this.stockQuantity = stockQuantity;
	}

    public String getProductId() {
		return productId;
	}
	public String getName() {
		return name;
	}
	public String getPrice() {
		return price;
	}
	public String getComment() {
		return comment;
	}
    public String[][] getComprisingParts() {
		return comprisingParts;
	}
    public int getStockQuantity() {
		return stockQuantity;
	}
}
