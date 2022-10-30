package com.remotegroup.businessintelligence.domain.model.aggregates;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.domain.AbstractAggregateRoot;

import com.remotegroup.businessintelligence.domain.model.commands.CreateNewBusinessIntelligenceCommand;
import com.remotegroup.businessintelligence.domain.model.commands.CreateSaleBusinessIntelligenceCommand;
import com.remotegroup.businessintelligence.domain.model.commands.UpdateBusinessIntelligenceCommand;
import com.remotegroup.businessintelligence.domain.model.valueobjects.Price;
import com.remotegroup.businessintelligence.domain.model.valueobjects.ProductName;
import com.remotegroup.businessintelligence.domain.model.valueobjects.Quantity;
import com.remotegroup.businessintelligence.domain.model.valueobjects.Total;

@Entity
public class BusinessIntelligence extends AbstractAggregateRoot<BusinessIntelligence> {
	private @Id @GeneratedValue Long id;

    @Embedded
    private BusinessIntelligenceId businessIntelligenceId;
    @Embedded
    private ProductName productName;
    @Embedded
    private Quantity quantity;
    @Embedded
    private Price price;
    @Embedded
    private Total total;

    public  BusinessIntelligence () {}

    public BusinessIntelligence(CreateNewBusinessIntelligenceCommand command) {
		this.businessIntelligenceId = new BusinessIntelligenceId(command.getBusinessIntelligenceId());
		this.productName = new ProductName(command.getProductName());
		this.quantity = new Quantity(command.getQuantity());
        this.price = new Price(command.getPrice());
        this.total = new Total(command.getTotal());
	}

    public BusinessIntelligence (CreateSaleBusinessIntelligenceCommand command) {
		this.businessIntelligenceId = new BusinessIntelligenceId(command.getBusinessIntelligenceId());
		this.productName = new ProductName(command.getProductName());
		this.quantity = new Quantity(command.getQuantity());
        this.price = new Price(command.getPrice());
        this.total = new Total(command.getTotal());
	}
	
	public BusinessIntelligence updateBusinessIntelligence(UpdateBusinessIntelligenceCommand command) {
		this.businessIntelligenceId = new BusinessIntelligenceId(command.getBusinessIntelligenceId());
		this.productName = new ProductName(command.getProductName());
		this.quantity = new Quantity(command.getQuantity());
        this.price = new Price(command.getPrice());
        this.total = new Total(command.getTotal());
		return this;
	}

    public Long getId(){
        return this.id;
    }

    public BusinessIntelligenceId getBusinessIntelligenceId(){
        return this.businessIntelligenceId;
    }

    public ProductName getProductName(){
        return this.productName;
    }

    public Quantity getQuantity() {
    	return this.quantity;
    }

    public Price getPrice() {
    	return this.price;
    }

    public Total getTotal() {
    	return this.total;
    }
    
    public void setBusinessIntelligenceId(BusinessIntelligenceId id) {
        this.businessIntelligenceId = id;
    }

    public void setProductName(ProductName n){
        this.productName = n;
    }
    
    public void setQuantity(Quantity q) {
    	this.quantity = q;
        this.total.calculate(this.price.toDouble(), this.quantity.toInteger());
    }

    public void setPrice(Price p) {
    	this.price = p;
        this.total.calculate(this.price.toDouble(), this.quantity.toInteger());
    }

    public boolean equals(BusinessIntelligence o) {
        if (this == o)
            return true;
        if (!(o instanceof BusinessIntelligence))
            return false;
            BusinessIntelligence businessIntelligence = (BusinessIntelligence) o;
        return Objects.equals(this.businessIntelligenceId, businessIntelligence.businessIntelligenceId) 
        && Objects.equals(this.productName, businessIntelligence.productName)
        && Objects.equals(this.quantity, businessIntelligence.quantity)
        && Objects.equals(this.price, businessIntelligence.price)
        && Objects.equals(this.total, businessIntelligence.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.businessIntelligenceId, this.productName, this.quantity, this.price, this.total);
    }

    @Override
    public String toString() {
        return "BusinessIntillgence{" + "businessIntelligenceId=" + this.businessIntelligenceId + '\''
        + ", productName='" + this.productName + '\'' 
        + ", quantity='" + this.quantity + '\'' 
        + ", price='" + this.price + '\''
         + ", total='" + this.total + '\''
         + '}';
    }
}