package com.remotegroup.sales.domain.model.commands;

public class CreateInStoreSaleCommand extends CreateSaleCommand {

	private String storeId;
	private String receiptNo;
	
	
	public CreateInStoreSaleCommand(String itemId, String itemName, String quantity, String dataTime, String productPrice, String storeId, String receiptNo) {
		super(itemId, itemName, quantity, dataTime, productPrice);
		this.storeId = storeId;
		this.receiptNo = receiptNo;
	}


	public String getStoreId() {
		return storeId;
	}

	public String getReceiptNo() {
		return receiptNo;
	}


	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
}
