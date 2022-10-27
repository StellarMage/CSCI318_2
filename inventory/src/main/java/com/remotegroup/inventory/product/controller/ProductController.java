package com.remotegroup.inventory.product.controller;

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
import com.remotegroup.inventory.part.domain.Part;
import com.remotegroup.inventory.product.domain.Product;
import com.remotegroup.inventory.service.InventoryService;
import com.remotegroup.inventory.shareddomain.BackOrderSale;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ProductController {
	
	@Autowired
	InventoryService inventoryService;
	
	//use case: get all products.
	@GetMapping("/products")
	List<Product> all() {
	  return inventoryService.getProducts();
	}
	
	//use case: create product
	@PostMapping("/product")
	Product newProduct(@RequestBody Product product) {
		return inventoryService.createProduct(product);
	}
	
	//use case: update product
	@PutMapping("/product/{id}")
	Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {
		return inventoryService.updateProduct(newProduct, id);
	}
	
	//use case: delete product
	@DeleteMapping("/product/{id}")
	void deleteProduct(@PathVariable Long id) {
		inventoryService.deleteProduct(id);
	}
	
	//use case: get product by id
	@GetMapping("/product/{id}")
	Product getProductById(@PathVariable Long id) {
		return inventoryService.getProduct(id);
	}

	//use case: Look up all parts by product
	@GetMapping("/product/parts/{id}")
	List<Part> getPartByProduct(@PathVariable Long id){
		return inventoryService.getPartByProduct(id);
	}
	
	@GetMapping("/product/check/{itemId}")
	boolean checkInventory(@PathVariable Long itemId) {
		return inventoryService.checkInventory(itemId);
	}
	
	@PostMapping("/product/request")
	boolean makeProcurementRequest(@RequestBody BackOrderSale b) {
		//inventoryService.procurementRequest(b);
		return true;
	}
	
}