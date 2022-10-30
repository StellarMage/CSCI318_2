package com.remotegroup.procurement.interfaces.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.remotegroup.procurement.domain.model.aggregates.Contact;
import com.remotegroup.procurement.domain.model.aggregates.ContactId;
import com.remotegroup.procurement.domain.model.commands.CreateContactCommand;
import com.remotegroup.procurement.domain.model.commands.UpdateContactCommand;
import com.remotegroup.procurement.domain.model.services.IProcurementService;

@RestController
public class ContactController {
	
	@Autowired
	IProcurementService procurementService;
	
	ContactController() {}

	//use case: looks up all contacts and transforms them into a REST collection resource.
	@GetMapping("/contacts")
	public
	CollectionModel<EntityModel<Contact>> all() {
		return procurementService.getContacts();
	}
	
	//use case: create contact
	@PostMapping("/contact")
	Contact newContact(@RequestBody CreateContactCommand command) {
		return procurementService.createContact(command);
	}
	
	//use case: update contact
	@PutMapping("/contact")
	Contact replaceContact(@RequestBody UpdateContactCommand command) {
		return procurementService.updateContact(command);
	}
	
	
	//use case: delete contact
	@DeleteMapping("/contact/{id}")
	void deleteContact(@PathVariable String id) {
		procurementService.deleteContact(new ContactId(id));
	}
	
	//use case: get contact by id

	@GetMapping("/contact/{id}")
	EntityModel<Contact> one(@PathVariable String id) {
		return procurementService.getContact(new ContactId(id));
	}
}
