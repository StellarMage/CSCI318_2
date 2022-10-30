package com.remotegroup.inventory.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remotegroup.inventory.domain.model.aggregates.Part;

public interface PartRepository extends JpaRepository<Part, Long>{

}