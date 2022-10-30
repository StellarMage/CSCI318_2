package com.remotegroup.procurement.interfaces.rest.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.remotegroup.procurement.domain.model.aggregates.Contact;

@Component
public
class ContactModelAssembler implements RepresentationModelAssembler<Contact, EntityModel<Contact>> {

  @Override
  public EntityModel<Contact> toModel(Contact contact) {

    return EntityModel.of(contact,
        linkTo(methodOn(ContactController.class).one(contact.getContactId().toString())).withSelfRel(),
        linkTo(methodOn(ContactController.class).all()).withRel("contacts"));
  }
}