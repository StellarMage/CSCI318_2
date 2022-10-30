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
    private ComprisingPart[] comprisingParts; 
    @Embedded
    private StockQuantity stockQuantity;
    
    public Product () {}

    public Product(CreateProductCommand command) {
		this.productId = new ProductId(command.getProductId());
		this.name = new Name(command.getName());
		this.price = new Price(command.getPrice());
        this.comment = new Comment(command.getComment());
        this.stockQuantity = new StockQuantity(command.getStockQuantity());
        
        String[][] partsStr = command.getComprisingParts();
        for(int i = 0; i < partsStr.length; i++) {
        	comprisingParts[i] = new ComprisingPart(new PartId(partsStr[i][0]), Long.parseLong(partsStr[i][1]));
        }
    }

    public Long getId(){
        return this.id;
    }
    public ProductId getProductId(){
        return this.productId;
    }
    public ProductId setProductId(ProductId productId){
        return this.productId;
    }
    public Name getName(){
        return this.name;
    }
    public Name setName(Name name){
        return this.name;
    }
    public Price getPrice(){
        return this.price;
    }
    public Price setPrice(Price price){
        return this.price;
    }
    public Comment getComment(){
        return this.comment;
    }
    public Comment setComment(Comment comment){
        return this.comment;
    }
    public ComprisingPart[] getComprisingParts(){
        return this.comprisingParts;
    }
    public ComprisingPart[] setComprisingParts(ComprisingPart[] comprisingParts){
        return this.comprisingParts;
    }
    public StockQuantity getStockQuantity(){
        return this.stockQuantity;
    }
    public StockQuantity setStockQuantity(StockQuantity stockQuantity){
        return this.stockQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Product))
            return false;
            Product product = (Product) o;
        return Objects.equals(this.productId, product.productId)
        && Objects.equals(this.name, product.name)
        && Objects.equals(this.price, product.price) 
        && Objects.equals(this.comment, product.comment)
        && Objects.equals(this.comprisingParts, product.comprisingParts)
        && Objects.equals(this.stockQuantity, product.stockQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.productId, this.name, this.price, this.comment);
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + this.productId + '\''
        + ", name='" + this.name + '\''
         + ", price='" + String.valueOf(this.price) + '\''
         + ", comment='" + this.comment + '\'' 
         + '}';
    }
}