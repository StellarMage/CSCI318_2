package com.remotegroup.shareddomain.model.commands;

public class UpdateStoreCommand {
	
	private String storeId;
	private String address;
	private String manager;
	
	public UpdateStoreCommand(String storeId, String address, String manager) {
		this.storeId = storeId;
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
}
