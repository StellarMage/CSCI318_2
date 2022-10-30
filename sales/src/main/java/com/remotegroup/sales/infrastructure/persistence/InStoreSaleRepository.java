package com.remotegroup.sales.infrastructure.persistence;

import com.remotegroup.sales.domain.model.aggregates.InStoreSale;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InStoreSaleRepository extends JpaRepository<InStoreSale, Long>{

}