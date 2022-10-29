package com.remotegroup.inventory.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remotegroup.inventory.domain.model.aggregates.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}