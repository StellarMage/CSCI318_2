package com.remotegroup.procurement.domain.model.services;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import com.remotegroup.procurement.domain.model.aggregates.Contact;
import com.remotegroup.procurement.domain.model.aggregates.Supplier;
import com.remotegroup.procurement.domain.model.aggregates.SupplierId;
import com.remotegroup.procurement.domain.model.commands.CreateSupplierCommand;

public interface IProcurementService {
	
	//expose use case abilities for suppliers
	public abstract void deleteSupplier(Long id);
	public abstract Supplier createSupplier(CreateSupplierCommand c);
	public abstract Supplier updateSupplier(Supplier s, SupplierId id);
	
	public abstract List<Supplier> getSuppliers();
	public abstract Supplier getSupplier(Long id);
	

	
	//expose use case abilities for contacts
	public abstract void deleteContact(Long id);
	public abstract Contact createContact(Contact c);
	public abstract Contact updateContact (Contact c, Long id);
	
	public abstract CollectionModel<EntityModel<Contact>> getContacts();
	public abstract EntityModel<Contact> getContact(Long id);

	//public abstract void procurementListener(String data);
}