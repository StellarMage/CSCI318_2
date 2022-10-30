package com.remotegroup.sales.domain.model.commands;

public class UpdateInStoreSaleCommand extends UpdateSaleCommand {
	
	private String inStoreSaleId;
	private String saleId;
	private String storeId;
	private String receiptNo;
	
	public UpdateInStoreSaleCommand(String saleId, String itemId, String itemName, String quantity, String dataTime, String productPrice, String storeId, String receiptNo) {
		super(saleId, itemId, itemName, quantity, dataTime, productPrice);
		this.storeId = storeId;
		this.receiptNo = receiptNo;
	}

	public String getInStoreSaleId() {
		return inStoreSaleId;
	}

	public String getSaleId() {
		return saleId;
	}

	public String getStoreId() {
		return storeId;
	}

	public String getReceiptNo() {
		return receiptNo;
	}
}
