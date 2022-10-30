package com.remotegroup.procurement.domain.model.commands;

public class UpdateContactCommand {
	
	private String contactId;
	private String supplierId;
	private String phone;
	private String email;
	private String name;
	private String position;
	
	public UpdateContactCommand(String contactId, String supplierId, String phone, String email, String name, String position) {
		this.contactId = contactId;
		this.supplierId = supplierId;
		this.phone = phone;
		this.email = email;
		this.name = name;
		this.position = position;
	}

	public String getContactId() {
		return contactId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getPosition() {
		return position;
	}

}
