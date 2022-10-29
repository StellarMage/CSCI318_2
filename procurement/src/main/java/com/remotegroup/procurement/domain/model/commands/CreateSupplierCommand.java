package com.remotegroup.procurement.domain.model.commands;

public class CreateSupplierCommand {

	private String supplierId;
	private String companyName;
	private String base;
	
	public CreateSupplierCommand(String companyName, String base) {
		this.companyName = companyName;
		this.base = base;
	}
	
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	
}
