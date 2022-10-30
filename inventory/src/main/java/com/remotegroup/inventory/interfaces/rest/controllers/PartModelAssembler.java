package com.remotegroup.inventory.interfaces.rest.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.remotegroup.inventory.domain.model.aggregates.Part;

public class PartModelAssembler implements RepresentationModelAssembler<Part, EntityModel<Part>> {
    
    @Override
    public EntityModel<Part> toModel(Part part) {

        return EntityModel.of(part,
            linkTo(methodOn(PartController.class).one(part.getId())).withSelfRel(),
            linkTo(methodOn(PartController.class).all()).withRel("parts"));
  }
}
