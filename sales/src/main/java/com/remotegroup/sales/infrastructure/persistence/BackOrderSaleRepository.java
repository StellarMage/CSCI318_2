package com.remotegroup.sales.infrastructure.persistence;

import com.remotegroup.sales.domain.model.aggregates.BackOrderSale;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BackOrderSaleRepository extends JpaRepository<BackOrderSale, Long> {

}
