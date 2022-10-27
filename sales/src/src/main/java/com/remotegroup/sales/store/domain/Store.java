package com.remotegroup.sales.store.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Store {
	private @Id @GeneratedValue Long id;
	String address;
	String manager;

	Store (){}
	
	public Store(String a, String m){
		address = a;
		manager = m;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getManager() {
		return manager;
	}
	
	public Long getStoreId() {
		return id;
	}
	
	public void setAddress(String newAddress) {
		this.address = newAddress;
	}
	
	public void setManager(String b) {
		this.manager = b;
	}
	
	public void setId(Long newId) {
		this.id = newId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Store))
            return false;
            Store store = (Store) o;
        return Objects.equals(this.id, store.id) 
        && Objects.equals(this.address, store.address)
        && Objects.equals(this.manager, store.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.address, this.manager);
    }

    @Override
    public String toString() {
        return "Store{" + "id=" + this.id + '\''
         + ", address='" + this.address + '\''
         + ", manager='" + this.manager + '\''
         + '}';
    }
}
