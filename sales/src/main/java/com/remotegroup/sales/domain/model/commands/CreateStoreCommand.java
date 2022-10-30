package com.remotegroup.sales.domain.model.commands;

public class CreateStoreCommand {

	private String storeId;
	private String address;
	private String manager;
	
	public CreateStoreCommand(String address, String manager) {
		this.address = address;
		this.manager = manager;
	}

	public String getStoreId() {
		return storeId;
	}

	public String getAddress() {
		return address;
	}

	public String getManager() {
		return manager;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
}
