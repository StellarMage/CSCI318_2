package com.remotegroup.procurement.domain.model.commands;

public class UpdateSupplierCommand {
	private String supplierId;
	private String companyName;
	private String base;
	
	public UpdateSupplierCommand(String supplierId, String companyName, String base) {
		this.supplierId = supplierId;
		this.companyName = companyName;
		this.base = base;
	}
	
	public String getSupplierId() {
		return supplierId;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public String getBase() {
		return base;
	}
	
}
