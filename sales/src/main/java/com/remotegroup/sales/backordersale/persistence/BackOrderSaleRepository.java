package com.remotegroup.sales.backordersale.persistence;

import com.remotegroup.sales.backordersale.domain.BackOrderSale;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BackOrderSaleRepository extends JpaRepository<BackOrderSale, Long> {

}
