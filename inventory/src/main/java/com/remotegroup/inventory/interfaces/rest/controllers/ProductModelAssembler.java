package com.remotegroup.inventory.interfaces.rest.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.remotegroup.inventory.domain.model.aggregates.Product;

public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {
    
    @Override
    public EntityModel<Product> toModel(Product product) {

        return EntityModel.of(product,
            linkTo(methodOn(ProductController.class).one(product.getProductId().toString())).withSelfRel(),
            linkTo(methodOn(ProductController.class).all()).withRel("products"));
  }
}
