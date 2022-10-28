package com.remotegroup.businessintelligence.businessIntelligence.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remotegroup.businessintelligence.businessIntelligence.domain.BusinessIntelligence;

public interface BusinessIntelligenceRepository extends JpaRepository<BusinessIntelligence, Long>{

}