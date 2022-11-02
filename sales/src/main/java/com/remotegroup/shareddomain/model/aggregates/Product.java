package com.remotegroup.shareddomain.model.aggregates;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AbstractAggregateRoot;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.remotegroup.shareddomain.model.commands.CreateProductCommand;
import com.remotegroup.shareddomain.model.commands.UpdateProductCommand;
import com.remotegroup.shareddomain.model.valueobjects.Comment;
import com.remotegroup.shareddomain.model.valueobjects.Name;
import com.remotegroup.shareddomain.model.valueobjects.Price;
import com.remotegroup.shareddomain.model.valueobjects.StockQuantity;

@Entity
public class Product extends AbstractAggregateRoot<Product>{
    private @Id @GeneratedValue Long id;
    
    @Embedded @JsonProperty("productId")
    private ProductId productId;
    @Embedded @JsonProperty("name")
    private Name name;
    @Embedded @JsonProperty("price")
    private Price price;
    @Embedded @JsonProperty("comment")
    private Comment comment;
    //@Embedded
    //private ComprisingPart[] comprisingParts; 
    @Embedded @JsonProperty("stockQuantity")
    private StockQuantity stockQuantity;

    private static final Logger log = LoggerFactory.getLogger(Product.class);
    
    public Product () {}

    public Product(CreateProductCommand command) {
		this.productId = new ProductId(command.getProductId());
		this.name = new Name(command.getName());
		this.price = new Price(command.getPrice());
        this.comment = new Comment(command.getComment());
        this.stockQuantity = new StockQuantity(command.getStockQuantity());
       // populateComprisingParts(command.getComprisingParts());

    }
    
    public Product updateProduct(UpdateProductCommand command) {
		this.name = new Name(command.getName());
		this.price = new Price(command.getPrice());
        this.comment = new Comment(command.getComment());
        this.stockQuantity = new StockQuantity(command.getStockQuantity());
        //populateComprisingParts(command.getComprisingParts());
		return this;
    }
    
    /*private void populateComprisingParts(String[][] c) {
        comprisingParts = new ComprisingPart[c.length];
    	for(int i = 0; i < c.length; i++) {
        	comprisingParts[i] = new ComprisingPart(new PartId(c[i][0]), Long.parseLong(c[i][1]));
            log.info("c: " + Arrays.deepToString(c));
            log.info("PartId: " + new PartId(c[i][0]));
            log.info("Long.parseLong: " + Long.parseLong(c[i][1]));
            log.info("comprisingParts: " + Arrays.deepToString(comprisingParts));
        }
    }*/
    
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
    /*public ComprisingPart[] getComprisingParts(){
        return this.comprisingParts;
    }
    public ComprisingPart[] setComprisingParts(ComprisingPart[] comprisingParts){
        return this.comprisingParts;
    }*/
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
        //&& Objects.equals(this.comprisingParts, product.comprisingParts)
        && Objects.equals(this.stockQuantity, product.stockQuantity);
    }

    public boolean equals(ProductId id) {
        if (this.productId == id)
            return true;
        if (!(id instanceof ProductId))
            return false;
        return Objects.equals(this.productId, id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.productId, this.name, this.price, this.comment);
    }

    @Override
    public String toString() {
        log.info("Product{" + "productId=" + this.productId + '\''
        + ", name='" + this.name + '\''
        + ", price='" + this.price + '\''
        + ", comment='" + this.comment + '\'' 
        //+ ", comprisingParts='" + Arrays.deepToString(this.comprisingParts) + '\''
        + ", stockQuantity='" + this.stockQuantity + '\''
        + '}');
        return "Product{" + "productId=" + this.productId.getValue() + '\''
        + ", name='" + this.name.getValue() + '\''
        + ", price='" + String.valueOf(this.price.getValue()) + '\''
        + ", comment='" + this.comment.getValue() + '\'' 
        //+ ", comprisingParts='" + Arrays.deepToString(this.comprisingParts) + '\''
        + ", stockQuantity='" + this.stockQuantity.getValue() + '\''
        + '}';
    }
}