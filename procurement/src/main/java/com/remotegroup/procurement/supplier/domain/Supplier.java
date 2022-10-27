package com.remotegroup.procurement;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Supplier {
	private @Id @GeneratedValue Long id;
	String companyName;
	String base;

	Supplier (){}
	
	public Supplier(String cN, String b){
		companyName = cN;
		base = b;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public String getBase() {
		return base;
	}
	
	public Long getSupplierId() {
		return id;
	}
	
	public void setCompanyName(String newName) {
		this.companyName = newName;
	}
	
	public void setBase(String b) {
		this.base = b;
	}
	
	public void setId(Long newId) {
		this.id = newId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Supplier))
            return false;
            Supplier supplier = (Supplier) o;
        return Objects.equals(this.id, supplier.id) 
        && Objects.equals(this.companyName, supplier.companyName)
        && Objects.equals(this.base, supplier.base);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.companyName, this.base);
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + this.id + '\''
         + ", name='" + this.companyName + '\''
         + ", base='" + this.base + '\''
         + '}';
    }
}
