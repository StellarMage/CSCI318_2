package com.remotegroup.procurement.application.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
import com.remotegroup.procurement.domain.model.aggregates.Supplier;
import com.remotegroup.procurement.domain.model.aggregates.SupplierId;
import com.remotegroup.procurement.domain.model.commands.CreateSupplierCommand;
import com.remotegroup.procurement.domain.model.services.IProcurementService;
import com.remotegroup.procurement.exceptions.ContactNotFoundException;
import com.remotegroup.procurement.exceptions.SupplierNotFoundException;
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
	public void deleteSupplier(Long id) {
		sRepo.deleteById(id);
	}

	@Override
	public List<Supplier> getSuppliers() {
		  return sRepo.findAll();
	}

	@Override
	public Supplier createSupplier(CreateSupplierCommand createSupplierCommand) {
		String supplierIdStr = UUID.randomUUID().toString().toUpperCase();
		createSupplierCommand.setSupplierId(supplierIdStr);
		
		return sRepo.save(new Supplier(createSupplierCommand));
	}

	@Override
	public Supplier updateSupplier(Supplier s, SupplierId id) {
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("supplierId", match->match.exact());
		
		Supplier sExample = new Supplier();
		sExample.setSupplierId(id);
		Example<Supplier> example = Example.of(sExample, matcher);
		
		List<Supplier> returnSuppliers =  sRepo.findAll(example);
		
		Supplier supplier = returnSuppliers.get(0);
		
		supplier.setCompanyName(s.getCompanyName());
		supplier.setBase(s.getBase());
		return sRepo.save(supplier);
		
		/*
		      	.map(Supplier -> {
					Supplier.setCompanyName(s.getCompanyName().toString());
					Supplier.setBase(s.getBase().toString());
		        return sRepo.save(Supplier);
		      })
		      	.orElseGet(() -> {
		        	s.setSupplierId(s.getSupplierId());
		        	return sRepo.save(s);
		      });*/
	}

	@Override
	public Supplier getSupplier(Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return sRepo.findById(id).get();
			
		}catch(Exception e) {
			throw new SupplierNotFoundException(id);
		}
	}

	// -------------- Contact 
	
	@Override
	public void deleteContact(Long id) {
		cRepo.deleteById(id);
		
	}

	@Override
	public CollectionModel<EntityModel<Contact>> getContacts() {
		List<EntityModel<Contact>> contacts = cRepo.findAll().stream() //
				.map(cAssembler::toModel) //
				.collect(Collectors.toList());

			return CollectionModel.of(contacts, linkTo(methodOn(ContactController.class).all()).withSelfRel());

	}

	@Override
	public Contact createContact(Contact c) {
		return cRepo.save(c);
	}

	@Override
	public Contact updateContact(Contact c, Long id) {
		return cRepo.findById(id)
		      	.map(Contact -> {
					Contact.setSupplierId(c.getSupplierId());
					Contact.setName(c.getName());
					Contact.setPhone(c.getPhone());
					Contact.setEmail(c.getEmail());
					Contact.setPosition(c.getPosition());
		        return cRepo.save(Contact);
		      })
		      	.orElseGet(() -> {
		        	c.setId(id);
		        	return cRepo.save(c);
		      });
	}

	@Override
	public EntityModel<Contact> getContact(Long id) {
		Contact contact = cRepo.findById(id) //
				.orElseThrow(() -> new ContactNotFoundException(id));

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
