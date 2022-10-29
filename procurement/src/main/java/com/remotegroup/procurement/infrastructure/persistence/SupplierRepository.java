package com.remotegroup.procurement.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remotegroup.procurement.domain.model.aggregates.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{

}
