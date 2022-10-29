package com.remotegroup.procurement.domain.model.aggregates;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.domain.AbstractAggregateRoot;

import com.remotegroup.procurement.domain.model.valueobjects.Email;
import com.remotegroup.procurement.domain.model.valueobjects.Name;
import com.remotegroup.procurement.domain.model.valueobjects.Phone;
import com.remotegroup.procurement.domain.model.valueobjects.Position;

@Entity
public class Contact extends AbstractAggregateRoot<Contact>{
    private @Id @GeneratedValue Long id;
    @Embedded
    private ContactId contactId;
    
    
    @Embedded
    private SupplierId supplierId;
	@Embedded
    private Name name;
    @Embedded
    private Phone phone;
    @Embedded
    private Email email;
    @Embedded
    private Position position;

    Contact () {}

    public Contact(String i, String n, String p, String e, String po){
        supplierId = i;
        name = n;
        phone = p;
        email = e;
        position = po;
    }
    public Long getId(){
        return this.id;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Contact))
            return false;
            Contact contact = (Contact) o;
        return Objects.equals(this.id, contact.id) 
        && Objects.equals(this.supplierId, contact.supplierId)
        && Objects.equals(this.name, contact.name)
        && Objects.equals(this.phone, contact.phone) 
        && Objects.equals(this.email, contact.email)
        && Objects.equals(this.position, contact.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.supplierId, this.name, this.phone, this.email, this.position);
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + this.id + '\''
        + ", supplier id='" + this.supplierId + '\'' 
        + ", name='" + this.name + '\''
         + ", phone='" + this.phone + '\''
         + ", email='" + this.email + '\'' 
         + ", position='" + this.position + '\'' 
         + '}';
    }
}
