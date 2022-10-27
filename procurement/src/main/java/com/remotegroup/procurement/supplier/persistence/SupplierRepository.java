package com.remotegroup.procurement.supplier.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remotegroup.procurement.supplier.domain.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{

}
