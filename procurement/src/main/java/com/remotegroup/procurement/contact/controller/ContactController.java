package com.remotegroup.procurement;

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

@RestController
public class ContactController {
	
	@Autowired
	ProcurementService procurementService;
	
	ContactController() {}

	//use case: looks up all contacts and transforms them into a REST collection resource.
	@GetMapping("/contacts")
	CollectionModel<EntityModel<Contact>> all() {
		return procurementService.getContacts();
	}
	
	//use case: create contact
	@PostMapping("/contact")
	Contact newContact(@RequestBody Contact newContact) {
		return procurementService.createContact(newContact);
	}
	
	//use case: update contact
	@PutMapping("/contact/{id}")
	Contact replaceContact(@RequestBody Contact newContact, @PathVariable Long id) {
		return procurementService.updateContact(newContact, id);
	}
	
	
	//use case: delete contact
	@DeleteMapping("/contact/{id}")
	void deleteContact(@PathVariable Long id) {
		procurementService.deleteContact(id);
	}
	
	//use case: get contact by id

	@GetMapping("/contact/{id}")
	EntityModel<Contact> one(@PathVariable Long id) {
		return procurementService.getContact(id);
	}
}
