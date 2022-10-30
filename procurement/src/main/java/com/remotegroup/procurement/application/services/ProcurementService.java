package com.remotegroup.procurement.application.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.remotegroup.procurement.domain.model.aggregates.Contact;
import com.remotegroup.procurement.domain.model.aggregates.ContactId;
import com.remotegroup.procurement.domain.model.aggregates.Supplier;
import com.remotegroup.procurement.domain.model.aggregates.SupplierId;
import com.remotegroup.procurement.domain.model.commands.CreateContactCommand;
import com.remotegroup.procurement.domain.model.commands.CreateSupplierCommand;
import com.remotegroup.procurement.domain.model.commands.UpdateContactCommand;
import com.remotegroup.procurement.domain.model.commands.UpdateSupplierCommand;
import com.remotegroup.procurement.domain.model.services.IProcurementService;
import com.remotegroup.procurement.infrastructure.persistence.ContactRepository;
import com.remotegroup.procurement.infrastructure.persistence.SupplierRepository;
import com.remotegroup.procurement.interfaces.rest.controllers.ContactController;
import com.remotegroup.procurement.interfaces.rest.controllers.ContactModelAssembler;

@Service
public class ProcurementService implements IProcurementService{

	@Autowired private final SupplierRepository sRepo;
	@Autowired private final ContactRepository cRepo;
	private final ContactModelAssembler cAssembler;
	ProcurementService(SupplierRepository sRepo, ContactRepository cRepo, ContactModelAssembler cAssembler){
		this.sRepo = sRepo;
		this.cRepo = cRepo;
		this.cAssembler = cAssembler;
	}
	
	@Override
	public void deleteSupplier(SupplierId id) {
		//find the supplier by supplierId
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("supplierId", match->match.exact());
		Supplier sExample = new Supplier();
		sExample.setSupplierId(id);
		Example<Supplier> example = Example.of(sExample, matcher);
		
		//store supplier in object
		List<Supplier> returnSuppliers =  sRepo.findAll(example);
		Supplier supplier = returnSuppliers.get(0);
		
		sRepo.delete(supplier);
	}

	@Override
	public List<Supplier> getSuppliers() {
		  return sRepo.findAll();
	}
	
	@Override
	public List<SupplierId> getSupplierIds(){
		List<SupplierId> ids = new ArrayList<SupplierId>();
		List<Supplier> suppliers = getSuppliers();
		for(int i=0;i<suppliers.size();i++) {
			ids.add(suppliers.get(i).getSupplierId());
		}
		return ids;
	}

	@Override
	public Supplier createSupplier(CreateSupplierCommand createSupplierCommand) {
		String supplierIdStr = UUID.randomUUID().toString().toUpperCase();
		createSupplierCommand.setSupplierId(supplierIdStr);
		
		return sRepo.save(new Supplier(createSupplierCommand));
	}

	@Override
	public Supplier updateSupplier(UpdateSupplierCommand updateSupplierCommand) {
		
		//find the supplier by supplierId
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("supplierId", match->match.exact());
		Supplier sExample = new Supplier();
		sExample.setSupplierId(new SupplierId(updateSupplierCommand.getSupplierId()));
		Example<Supplier> example = Example.of(sExample, matcher);
		
		//store supplier in object
		List<Supplier> returnSuppliers =  sRepo.findAll(example);
		Supplier supplier = returnSuppliers.get(0);
		
		//update supplier
		supplier.updateSupplier(updateSupplierCommand);
		
		//save to repository
		return sRepo.save(supplier);
		}

	@Override
	public Supplier getSupplier(SupplierId id) {
			//find the supplier by supplierId
			ExampleMatcher matcher = ExampleMatcher.matching()
					.withMatcher("supplierId", match->match.exact());
			Supplier sExample = new Supplier();
			sExample.setSupplierId(id);
			Example<Supplier> example = Example.of(sExample, matcher);
			
			//store supplier in object
			List<Supplier> returnSuppliers =  sRepo.findAll(example);
			Supplier supplier = returnSuppliers.get(0);
			return supplier;
			
	}

	// -------------- Contact 
	
	@Override
	public void deleteContact(ContactId id) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("contactId", match->match.exact());
		Contact sExample = new Contact();
		sExample.setContactId(id);
		Example<Contact> example = Example.of(sExample, matcher);
		
		List<Contact> returnContacts =  cRepo.findAll(example);
		Contact contact = returnContacts.get(0);
		cRepo.delete(contact);
		
	}

	@Override
	public CollectionModel<EntityModel<Contact>> getContacts() {
		List<EntityModel<Contact>> contacts = cRepo.findAll().stream() //
				.map(cAssembler::toModel) //
				.collect(Collectors.toList());

			return CollectionModel.of(contacts, linkTo(methodOn(ContactController.class).all()).withSelfRel());

	}

	@Override
	public Contact createContact(CreateContactCommand command) {
		String contactIdStr = UUID.randomUUID().toString().toUpperCase();
		command.setContactId(contactIdStr);
		return cRepo.save(new Contact(command));
	}

	@Override
	public Contact updateContact(UpdateContactCommand command) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("contactId", match->match.exact());
		Contact sExample = new Contact();
		sExample.setContactId(new ContactId(command.getContactId()));
		Example<Contact> example = Example.of(sExample, matcher);
		
		List<Contact> returnContacts =  cRepo.findAll(example);
		Contact contact = returnContacts.get(0);
		
		contact.updateContact(command);
		
		return cRepo.save(contact);
	}

	@Override
	public EntityModel<Contact> getContact(ContactId id) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("contactId", match->match.exact());
		Contact sExample = new Contact();
		sExample.setContactId(id);
		Example<Contact> example = Example.of(sExample, matcher);
		
		List<Contact> returnContacts =  cRepo.findAll(example);
		Contact contact = returnContacts.get(0);
			return cAssembler.toModel(contact);
	}

	/*
	@Override
	@KafkaListener(
        topics = "remotegroup",
        groupId = "groupId")
	public void procurementListener(String data) {
        System.out.println("Back order received:");
        System.out.println(data);
    }
    */
}
