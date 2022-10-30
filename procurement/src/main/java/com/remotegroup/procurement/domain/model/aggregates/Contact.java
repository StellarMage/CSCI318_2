package com.remotegroup.procurement.domain.model.aggregates;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.domain.AbstractAggregateRoot;

import com.remotegroup.procurement.domain.model.commands.CreateContactCommand;
import com.remotegroup.procurement.domain.model.commands.UpdateContactCommand;
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

    public Contact () {}

    public Contact(CreateContactCommand command){
        this.contactId = new ContactId(command.getContactId());
        this.supplierId = new SupplierId(command.getSupplierId());
        this.name = new Name(command.getName());
        this.phone = new Phone(command.getPhone());
        this.email = new Email(command.getEmail());
        this.position = new Position(command.getPosition());
    }
    
    public Contact updateContact(UpdateContactCommand command) {
        this.contactId = new ContactId(command.getContactId());
        this.supplierId = new SupplierId(command.getSupplierId());
        this.name = new Name(command.getName());
        this.phone = new Phone(command.getPhone());
        this.email = new Email(command.getEmail());
        this.position = new Position(command.getPosition());
        return this;
    }
    
    public Long getId(){
        return this.id;
    }
    
    


    public ContactId getContactId() {
		return contactId;
	}

	public SupplierId getSupplierId() {
		return supplierId;
	}

	public Name getName() {
		return name;
	}

	public Phone getPhone() {
		return phone;
	}

	public Email getEmail() {
		return email;
	}

	public Position getPosition() {
		return position;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSupplierId(SupplierId supplierId) {
		this.supplierId = supplierId;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public void setPosition(Position position) {
		this.position = position;
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
        return Objects.hash(this.id, this.contactId, this.supplierId, this.name, this.phone, this.email, this.position);
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

	public void setContactId(ContactId contactId2) {
		this.contactId = contactId2;
		
	}
}
