package com.remotegroup.procurement.domain.model.services;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import com.remotegroup.procurement.domain.model.aggregates.Contact;
import com.remotegroup.procurement.domain.model.aggregates.ContactId;
import com.remotegroup.procurement.domain.model.aggregates.Supplier;
import com.remotegroup.procurement.domain.model.aggregates.SupplierId;
import com.remotegroup.procurement.domain.model.commands.CreateContactCommand;
import com.remotegroup.procurement.domain.model.commands.CreateSupplierCommand;
import com.remotegroup.procurement.domain.model.commands.UpdateContactCommand;
import com.remotegroup.procurement.domain.model.commands.UpdateSupplierCommand;

public interface IProcurementService {
	
	//expose use case abilities for suppliers
	public abstract void deleteSupplier(SupplierId id);
	public abstract Supplier createSupplier(CreateSupplierCommand c);
	public abstract Supplier updateSupplier(UpdateSupplierCommand c);
	
	public abstract List<Supplier> getSuppliers();
	public abstract Supplier getSupplier(SupplierId id);
	

	
	//expose use case abilities for contacts
	public abstract void deleteContact(ContactId id);
	public abstract Contact createContact(CreateContactCommand c);
	public abstract Contact updateContact (UpdateContactCommand c);
	
	public abstract CollectionModel<EntityModel<Contact>> getContacts();
	public abstract EntityModel<Contact> getContact(ContactId id);

	//public abstract void procurementListener(String data);
}
