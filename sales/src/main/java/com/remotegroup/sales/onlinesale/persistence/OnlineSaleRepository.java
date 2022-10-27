package com.remotegroup.sales.onlinesale.persistence;

import com.remotegroup.sales.onlinesale.domain.OnlineSale;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OnlineSaleRepository extends JpaRepository<OnlineSale, Long>{

}