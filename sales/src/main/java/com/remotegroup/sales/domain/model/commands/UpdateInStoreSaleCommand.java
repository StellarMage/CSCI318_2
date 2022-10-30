package com.remotegroup.sales.domain.model.commands;

public class UpdateInStoreSaleCommand {
	
	private String inStoreSaleId;
	private String saleId;
	private String storeId;
	private String receiptNo;
	
	public UpdateInStoreSaleCommand(String inStoreSaleId, String saleId, String storeId, String receiptNo) {
		this.inStoreSaleId = inStoreSaleId;
		this.saleId = saleId;
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
