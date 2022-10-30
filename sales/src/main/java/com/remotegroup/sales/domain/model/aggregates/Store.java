package com.remotegroup.sales.domain.model.aggregates;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.domain.AbstractAggregateRoot;

import com.remotegroup.sales.domain.model.commands.CreateStoreCommand;
import com.remotegroup.sales.domain.model.commands.UpdateStoreCommand;
import com.remotegroup.sales.domain.model.valueobjects.Address;
import com.remotegroup.sales.domain.model.valueobjects.Manager;

@Entity
public class Store extends AbstractAggregateRoot<Store> {
	private @Id @GeneratedValue Long id;
	
	@Embedded
	private StoreId storeId;
	@Embedded
	private Address address;
	@Embedded
	private Manager manager;

	public Store (){}
	
	public Store(CreateStoreCommand command) {
		this.storeId = new StoreId(command.getStoreId());
		this.address = new Address(command.getAddress());
		this.manager = new Manager(command.getManager());
	}
	
	public Store updateStore(UpdateStoreCommand command) {
		this.storeId = new StoreId(command.getStoreId());
		this.address = new Address(command.getAddress());
		this.manager = new Manager(command.getManager());
		return this;
		
	}

	public Long getId() {
		return this.id;
	}

	public StoreId getStoreId() {
		return this.storeId;
	}
	
	public Address getAddress() {
		return this.address;
	}
	
	public Manager getManager() {
		return this.manager;
	}

	public void setStoreId(StoreId newId) {
		this.storeId = newId;
	}
	
	public void setAddress(Address newAddress) {
		this.address = newAddress;
	}
	
	public void setManager(Manager b) {
		this.manager = b;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Store))
            return false;
            Store store = (Store) o;
        return Objects.equals(this.storeId, store.storeId) 
        && Objects.equals(this.address, store.address)
        && Objects.equals(this.manager, store.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.storeId, this.address, this.manager);
    }

    @Override
    public String toString() {
        return "Store{" + "storeId=" + this.storeId + '\''
         + ", address='" + this.address + '\''
         + ", manager='" + this.manager + '\''
         + '}';
    }
}
