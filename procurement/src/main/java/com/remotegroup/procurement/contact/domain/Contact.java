package com.remotegroup.procurement;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Contact {
    private @Id @GeneratedValue Long id;
    Long supplierId;
    String name;
    String phone;
    String email;
    String position;

    Contact () {}

    public Contact(Long i, String n, String p, String e, String po){
        supplierId = i;
        name = n;
        phone = p;
        email = e;
        position = po;
    }
    public Long getId(){
        return this.id;
    }

    public Long getSupplierId(){
        return this.supplierId;
    }

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }

    public String getEmail(){
        return email;
    }

    public String getPosition(){
        return position;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSupplierId(Long id) {
        this.supplierId = id;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setPhone(String newPhone){
        this.phone = newPhone;
    }

    public void setEmail(String newEmail){
        this.email = newEmail;
    }

    public void setPosition(String newPosition){
        this.position = newPosition;
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
