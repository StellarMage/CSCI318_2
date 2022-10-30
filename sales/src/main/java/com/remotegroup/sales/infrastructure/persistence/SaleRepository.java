package com.remotegroup.sales.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remotegroup.sales.domain.model.aggregates.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{

}