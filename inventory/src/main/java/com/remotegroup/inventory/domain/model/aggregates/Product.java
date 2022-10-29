package com.remotegroup.inventory.domain.model.aggregates;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.domain.AbstractAggregateRoot;

import com.remotegroup.inventory.domain.model.valueobjects.Name;
import com.remotegroup.inventory.domain.model.valueobjects.Price;
import com.remotegroup.inventory.domain.model.commands.CreateProductCommand;
import com.remotegroup.inventory.domain.model.valueobjects.Comment;
import com.remotegroup.inventory.domain.model.valueobjects.CompromisingParts;
import com.remotegroup.inventory.domain.model.valueobjects.StockQuantity;

@Entity
public class Product extends AbstractAggregateRoot<Product>{
    private @Id @GeneratedValue Long id;
    @Embedded
    private ProductId productId;

    @Embedded
    private Name name;
    @Embedded
    private Price price;
    @Embedded
    private Comment comment;
    @Embedded
    private CompromisingParts[][] comprisingParts; // [][0] = part_id, [][1] = parts_needed  NOTE: MUST be >= 1 Part with number >= 1
    @Embedded
    private StockQuantity stockQuantity;
    
    Product () {}

    public Product(CreateProductCommand command) {
		this.productId = new ProductId(command.getProductId());
		this.name = new Name(command.getName());
		this.price = new Price(command.getPrice());
        this.comment = new Comment(command.getComment());
        this.comprisingParts = new CompromisingParts(command.getCompromisingParts());
        this.stockQuantity = new StockQuantity(command.getStockQuantity());
	}

    public Long getId(){
        return this.id;
    }

    public Name getName(){
        return name;
    }

    public Price getPrice(){
        return price;
    }

    public Comment getComment(){
        return comment;
    }
    
    public Long[][] getComprisingParts() {
    	return this.comprisingParts;
    }
    
    public StockQuantity getStockQuantity() {
    	return this.stockQuantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(Name newName){
        this.name = newName;
    }

    public void setPrice(Price newPrice){
        this.price = newPrice;
    }

    public void setComment(Comment newComment){
        this.comment = newComment;
    }
    
    public void setComprisingParts(Long[][] cp) {
    	this.comprisingParts = cp.clone();
    }
    
    public void setStockQuantity(StockQuantity s) {
    	this.stockQuantity = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Product))
            return false;
            Product product = (Product) o;
        return Objects.equals(this.name, product.name)
        && Objects.equals(this.price, product.price) 
        && Objects.equals(this.comment, product.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.price, this.comment);
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + this.id + '\''
        + ", name='" + this.name + '\''
         + ", price='" + String.valueOf(this.price) + '\''
         + ", comment='" + this.comment + '\'' 
         + '}';
    }
}