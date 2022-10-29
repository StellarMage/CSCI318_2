package com.remotegroup.procurement.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remotegroup.procurement.domain.model.aggregates.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
