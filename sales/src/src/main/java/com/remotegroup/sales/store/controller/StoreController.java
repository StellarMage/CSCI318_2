package com.remotegroup.sales.store.controller;

import java.util.List;
import java.util.function.Function;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class StoreController {
	
	private final StoreRepository repository;
	StoreController(StoreRepository repository){
		this.repository = repository;
	}
	
	//use case: get all stores.
	@GetMapping("/stores")
	List<Store> all() {
	  return repository.findAll();
	}
	
	//use case: create store
	@PostMapping("/store")
	Store newStore(@RequestBody Store store) {
		return repository.save(store);
	}
	
	//use case: update store
	@PutMapping("/store/{id}")
	Store replaceStore(@RequestBody Store newStore, @PathVariable Long id) {
		return repository.findById(id)
      	.map(Store -> {
			Store.setAddress(newStore.getAddress());
			Store.setManager(newStore.getManager());
        return repository.save(Store);
      })
      	.orElseGet(() -> {
        	newStore.setId(id);
        	return repository.save(newStore);
      });
	}
	
	//use case: delete store
	@DeleteMapping("/store/{id}")
	void deleteStore(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	//use case: get store by id
	@GetMapping("/store/{id}")
	Store getStoreById(@PathVariable Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return repository.findById(id).get();
			
		}catch(Exception e) {
			throw new StoreNotFoundException(id);
		}
	}
	
}
