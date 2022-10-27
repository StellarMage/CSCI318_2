package com.remotegroup.inventory.part.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remotegroup.inventory.part.domain.Part;

public interface PartRepository extends JpaRepository<Part, Long>{

}