package com.remotegroup.inventory.product.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remotegroup.inventory.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}