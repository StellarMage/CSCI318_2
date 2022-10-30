package com.remotegroup.procurement.domain.model.commands;

public class CreateContactCommand {

	private String contactId;
	private String supplierId;
	private String phone;
	private String email;
	private String name;
	private String position;
	
	public CreateContactCommand(String supplierId, String phone, String email, String name, String position) {
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

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	
	
}
