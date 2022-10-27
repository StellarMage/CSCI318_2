package com.remotegroup.sales.sale.persistence;

import com.remotegroup.sales.sale.domain.Sale;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long>{

}