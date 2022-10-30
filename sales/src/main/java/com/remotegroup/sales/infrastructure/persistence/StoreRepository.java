package com.remotegroup.sales.infrastructure.persistence;

import com.remotegroup.sales.domain.model.aggregates.Store;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long>{

}
