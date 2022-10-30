package com.remotegroup.inventory.domain.model.aggregates;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.domain.AbstractAggregateRoot;

import com.remotegroup.inventory.domain.model.valueobjects.SupplierId;
import com.remotegroup.inventory.domain.model.valueobjects.Name;
import com.remotegroup.inventory.domain.model.commands.CreatePartCommand;
import com.remotegroup.inventory.domain.model.valueobjects.Description;
import com.remotegroup.inventory.domain.model.valueobjects.StockQuantity;

@Entity
public class Part extends AbstractAggregateRoot<Part>{
    private @Id @GeneratedValue Long id;

    @Embedded
    private PartId partId;
    @Embedded
    private SupplierId supplierId;
    @Embedded
    private Name name;
    @Embedded
    private Description description;
    @Embedded
    private StockQuantity stockQuantity;

    public Part () {}

    public Part(CreatePartCommand command) {
        this.partId = new PartId(command.getPartId());
		this.supplierId = new SupplierId(command.getSupplierId());
		this.name = new Name(command.getName());
		this.description = new Description(command.getDescription());
        this.stockQuantity = new StockQuantity(command.getStockQuantity());
	}
    
    public Long getId(){
        return this.id;
    }
    public PartId getPartId(){
        return this.partId;
    }
    public PartId setPartId(PartId partId){
        return this.partId;
    }
    public SupplierId getSupplierId(){
        return this.supplierId;
    }
    public SupplierId setSupplierId(SupplierId supplierId){
        return this.supplierId;
    }
    public Name getName(){
        return this.name;
    }
    public Name setName(Name name){
        return this.name;
    }
    public Description getDescription(){
        return this.description;
    }
    public Description setDescription(Description description){
        return this.description;
    }
    public StockQuantity getStockQuantity(){
        return this.stockQuantity;
    }
    public StockQuantity setStockQuantity(StockQuantity stockQuantity){
        return this.stockQuantity;
    }

    @Override
    public boolean equals(Part o) {
        if (this == o)
            return true;
        if (!(o instanceof Part))
            return false;
            Part part = (Part) o;
        return Objects.equals(this.partId, part.partId) 
        && Objects.equals(this.supplierId, part.supplierId)
        && Objects.equals(this.name, part.name)
        && Objects.equals(this.description, part.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.partId, this.supplierId, this.name, this.description);
    }

    @Override
    public String toString() {
        return "Part{" + "partId=" + this.partId + '\''
        + ", supplier id='" + this.supplierId + '\'' 
        + ", name='" + this.name + '\''
         + ", description='" + this.description + '\''
         + '}';
    }
}