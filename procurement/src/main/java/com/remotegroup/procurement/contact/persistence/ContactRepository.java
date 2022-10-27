package com.remotegroup.procurement.contact.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remotegroup.procurement.contact.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
