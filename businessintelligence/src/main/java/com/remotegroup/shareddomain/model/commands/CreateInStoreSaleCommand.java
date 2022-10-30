
package com.remotegroup.shareddomain.model.commands;

public class CreateInStoreSaleCommand {

	private String inStoreSaleId;
	private String saleId;
	private String storeId;
	private String receiptNo;
	
	public CreateInStoreSaleCommand(String saleId, String storeId, String receiptNo) {
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

	public String setInStoreSaleId() {
		return inStoreSaleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
}
