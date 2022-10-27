package com.remotegroup.procurement;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface ProcurementService {
	
	//expose Supplier endpoints
	public abstract void deleteSupplier(Long id);
	public abstract List<Supplier> getSuppliers();
	public abstract Supplier createSupplier(Supplier s);
	public abstract Supplier updateSupplier(Supplier s, Long id);
	public abstract Supplier getSupplier(Long id);
	
	//expose Contact endpoints
	public abstract void deleteContact(Long id);
	public abstract CollectionModel<EntityModel<Contact>> getContacts();
	public abstract Contact createContact(Contact c);
	public abstract Contact updateContact (Contact c, Long id);
	public abstract EntityModel<Contact> getContact(Long id);

	//public abstract void procurementListener(String data);
}
