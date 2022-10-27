package com.remotegroup.procurement;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProcurementServiceImpl implements ProcurementService{

	private final SupplierRepository sRepo;
	private final ContactRepository cRepo;
	private final ContactModelAssembler cAssembler;
	ProcurementServiceImpl(SupplierRepository sRepo, ContactRepository cRepo, ContactModelAssembler cAssembler){
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
	public Supplier createSupplier(Supplier s) {
		return sRepo.save(s);
	}

	@Override
	public Supplier updateSupplier(Supplier s, Long id) {
		return sRepo.findById(id)
		      	.map(Supplier -> {
					Supplier.setCompanyName(s.getCompanyName());
					Supplier.setBase(s.getBase());
		        return sRepo.save(Supplier);
		      })
		      	.orElseGet(() -> {
		        	s.setId(id);
		        	return sRepo.save(s);
		      });
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
