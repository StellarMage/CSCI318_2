package com.remotegroup.procurement.domain.model.aggregates;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.domain.AbstractAggregateRoot;

import com.remotegroup.procurement.domain.model.commands.CreateSupplierCommand;
import com.remotegroup.procurement.domain.model.commands.UpdateSupplierCommand;
import com.remotegroup.procurement.domain.model.valueobjects.Base;
import com.remotegroup.procurement.domain.model.valueobjects.CompanyName;

@Entity
public class Supplier extends AbstractAggregateRoot<Supplier> {
	private @Id @GeneratedValue Long id;
	
	@Embedded
	private SupplierId supplierId; //aggregate identifier
	@Embedded
	private CompanyName companyName;
	@Embedded
	private Base base;

	public Supplier (){}
	
	/*This function is redundant, only create supplier via controller or service, with CreateSupplierCommand
	 * public Supplier(String cN, String b){
		companyName = new CompanyName(cN);
		base = new Base(b);
	}*/
	
	public Supplier(CreateSupplierCommand command) {
		this.supplierId = new SupplierId(command.getSupplierId());
		this.companyName = new CompanyName(command.getCompanyName());
		this.base = new Base(command.getBase());
	}
	
	public Supplier updateSupplier(UpdateSupplierCommand command) {
		this.supplierId = new SupplierId(command.getSupplierId());
		this.companyName = new CompanyName(command.getCompanyName());
		this.base = new Base(command.getBase());
		return this;
		
	}
	
	public CompanyName getCompanyName() {
		return companyName;
	}
	
	public Base getBase() {
		return base;
	}
	
	public SupplierId getSupplierId() {
		return supplierId;
	}
	
	public Long getId() {return this.id;}
	
	public void setCompanyName(CompanyName newName) {
		this.companyName = newName;
	}
	
	public void setBase(Base b) {
		this.base = b;
	}
	
	public void setSupplierId(SupplierId newId) {
		this.supplierId = newId;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Supplier))
            return false;
            Supplier supplier = (Supplier) o;
        return Objects.equals(this.supplierId, supplier.supplierId) 
        && Objects.equals(this.companyName, supplier.companyName)
        && Objects.equals(this.base, supplier.base);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.supplierId, this.companyName, this.base);
    }

    @Override
    public String toString() {
        return "Contact{" + "supplierId=" + this.supplierId + '\''
         + ", name='" + this.companyName + '\''
         + ", base='" + this.base + '\''
         + '}';
    }
}
