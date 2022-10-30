package com.remotegroup.sales.infrastructure.persistence;

import com.remotegroup.sales.domain.model.aggregates.OnlineSale;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OnlineSaleRepository extends JpaRepository<OnlineSale, Long>{

}