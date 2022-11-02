package com.remotegroup.inventory.domain.model.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateProductCommand {
    
    private String productId;
    private String name;
    private String price;
    private String comment;
    //private String[][] comprisingParts; // [ [partId, amount], ... ]
    private int stockQuantity;
	private static final Logger log = LoggerFactory.getLogger(CreateProductCommand.class);

    public CreateProductCommand(String name, String price, String comment, /*String[][] comprisingParts,*/ int stockQuantity) {
		this.name = name;
		this.price = price;
        this.comment = comment;
        //this.comprisingParts = comprisingParts;
        this.stockQuantity = stockQuantity;
	}

    public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
    /*public String[][] getComprisingParts() {
		return comprisingParts;
	}
	public void setComprisingParts(String[][] comprisingParts) {
		this.comprisingParts = comprisingParts;
	}*/
    public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
    }
}