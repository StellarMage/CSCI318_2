package com.remotegroup.businessintelligence.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remotegroup.businessintelligence.domain.model.aggregates.BusinessIntelligence;

public interface BusinessIntelligenceRepository extends JpaRepository<BusinessIntelligence, Long>{

}