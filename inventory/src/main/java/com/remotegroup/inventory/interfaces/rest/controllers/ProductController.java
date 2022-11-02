package com.remotegroup.inventory.interfaces.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.remotegroup.inventory.domain.model.aggregates.Part;
import com.remotegroup.inventory.domain.model.aggregates.Product;
import com.remotegroup.inventory.domain.model.aggregates.ProductId;
import com.remotegroup.inventory.domain.model.commands.CreateProductCommand;
import com.remotegroup.inventory.domain.model.commands.UpdateProductCommand;
import com.remotegroup.inventory.domain.model.services.IInventoryService;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ProductController {
	
	@Autowired
	IInventoryService inventoryService;
	
	//use case: get all products.
	@GetMapping("/products")
	List<Product> all() {
	  return inventoryService.getProducts();
	}
	
	@GetMapping("/products/ids")
	List<ProductId> getProductIds(){
		return inventoryService.getProductIds();
	}
	
	//use case: create product
	@PostMapping("/product")
	Product newProduct(@RequestBody CreateProductCommand c) {
		return inventoryService.createProduct(c);
	}
	
	//use case: update product
	@PutMapping("/product/{id}")
	Product replaceProduct(@RequestBody UpdateProductCommand c) {
		return inventoryService.updateProduct(c);
	}
	
	//use case: delete product
	@DeleteMapping("/product/{id}")
	void deleteProduct(@PathVariable String id) {
		inventoryService.deleteProduct(new ProductId(id));
	}
	
	//use case: get product by id
	@GetMapping("/product/{id}")
	Product one(@PathVariable String id) {
		return inventoryService.getProduct(new ProductId(id));
	}

	//use case: Look up all parts by product
	@GetMapping("/product/parts/{id}")
	List<Part> getPartByProduct(@PathVariable String id){
		return inventoryService.getPartByProduct(new ProductId(id));
	}
	
	@GetMapping("/product/check/{itemId}")
	boolean checkInventory(@PathVariable String itemId) {
		return inventoryService.checkInventory(new ProductId(itemId));
	}
	
	/*@PostMapping("/product/request")
	boolean makeProcurementRequest(@RequestBody BackOrderSale b) {
		inventoryService.procurementRequest(b);
		return true;
	}*/
	
}