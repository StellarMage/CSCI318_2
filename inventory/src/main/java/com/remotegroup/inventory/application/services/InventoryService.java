package com.remotegroup.inventory.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.remotegroup.inventory.domain.model.aggregates.ComprisingPart;
import com.remotegroup.inventory.domain.model.aggregates.Part;
import com.remotegroup.inventory.domain.model.aggregates.PartId;
import com.remotegroup.inventory.domain.model.aggregates.Product;
import com.remotegroup.inventory.domain.model.aggregates.ProductId;
import com.remotegroup.inventory.domain.model.commands.CreatePartCommand;
import com.remotegroup.inventory.domain.model.commands.CreateProductCommand;
import com.remotegroup.inventory.domain.model.commands.UpdatePartCommand;
import com.remotegroup.inventory.domain.model.commands.UpdateProductCommand;
import com.remotegroup.inventory.domain.model.services.IInventoryService;
import com.remotegroup.inventory.domain.model.valueobjects.SupplierId;
import com.remotegroup.inventory.infrastructure.persistence.PartRepository;
import com.remotegroup.inventory.infrastructure.persistence.ProductRepository;

@Service
public class InventoryService implements IInventoryService{

	@Autowired private final ProductRepository prRepo;
	//@Autowired private final PartModelAssembler prAssembler;
	@Autowired private final PartRepository paRepo;
	//@Autowired private final PartModelAssembler paAssembler;
	
	InventoryService(ProductRepository prRepo, PartRepository paRepo){
		this.paRepo = paRepo;
		this.prRepo = prRepo;
	}
	
	@Override
	public List<Product> getProducts() {
		return prRepo.findAll();
	}

	@Override
	public Product createProduct(CreateProductCommand createProductCommand) {
		String productIdStr = UUID.randomUUID().toString().toUpperCase();
		createProductCommand.setProductId(productIdStr);

		return prRepo.save(new Product(createProductCommand));
	}

	@Override
	public Product updateProduct(UpdateProductCommand command) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("productId", match->match.exact());
		
		Product pExample = new Product();
		pExample.setProductId(new ProductId(command.getProductId()));
		Example<Product> example = Example.of(pExample, matcher);
		
		List<Product> returnProducts =  prRepo.findAll(example);
		
		Product product = returnProducts.get(0);
		product.updateProduct(command);
		return prRepo.save(product);
	}

	@Override
	public void deleteProduct(ProductId id) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("productId", match->match.exact());
		
		Product pExample = new Product();
		pExample.setProductId(id);
		Example<Product> example = Example.of(pExample, matcher);
		
		List<Product> returnProducts =  prRepo.findAll(example);
		
		Product product = returnProducts.get(0);
		prRepo.delete(product);
		
	}
	
	@Override
	public List<String> getProductIds(){
		List<String> ids = new ArrayList<String>();
		List<Product> products = getProducts();
		for(int i=0;i<products.size();i++) {
			ids.add(products.get(i).getProductId().toString());
		}
		return ids;
	}
	
	@Override
	public Product getProduct(ProductId id) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("productId", match->match.exact());
		
		Product pExample = new Product();
		pExample.setProductId(id);
		Example<Product> example = Example.of(pExample, matcher);
		
		List<Product> returnProducts =  prRepo.findAll(example);
		
		Product product = returnProducts.get(0);
		return product;
	}

	@Override
	public List<Part> getPartByProduct(ProductId id){
		Product p = getProduct(id);

		ComprisingPart[] parts = p.getComprisingParts();

		List<Part> pList = new ArrayList<Part>();
		for(int c=0; c<parts.length; c++) {
			PartId partId = parts[c].getPart();
			pList.add(getPart(partId));
		}
		return pList;
		
	}

	
	//================================== Parts
	
	@Override
	public List<Part> getParts() {
		  return paRepo.findAll();
	}

	@Override
	public Part createPart(CreatePartCommand createPartCommand) {
		String partIdStr = UUID.randomUUID().toString().toUpperCase();
		createPartCommand.setPartId(partIdStr);

		return paRepo.save(new Part(createPartCommand));
	}

	@Override
	public Part updatePart(UpdatePartCommand command) {
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("partId", match->match.exact());
		
		Part sExample = new Part();
		sExample.setPartId(new PartId(command.getPartId()));
		Example<Part> example = Example.of(sExample, matcher);
		
		List<Part> returnParts =  paRepo.findAll(example);
		
		Part part = returnParts.get(0);
		
		part.updatePart(command);
		return paRepo.save(part);
	}

	@Override
	public void deletePart(PartId id) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("partId", match->match.exact());
		
		Part sExample = new Part();
		sExample.setPartId(id);
		Example<Part> example = Example.of(sExample, matcher);
		
		List<Part> returnParts =  paRepo.findAll(example);
		
		Part part = returnParts.get(0);
		
		paRepo.delete(part);
		
	}

	@Override
	public Part getPart(PartId id) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("partId", match->match.exact());
		
		Part sExample = new Part();
		sExample.setPartId(id);
		Example<Part> example = Example.of(sExample, matcher);
		
		List<Part> returnParts =  paRepo.findAll(example);
		
		Part part = returnParts.get(0);
		return part;
	}
	
	@Override
	public SupplierId getPartSupplier(PartId id) {
		Part chosenPart = getPart(id);
		return chosenPart.getSupplierId();
	}

	@Override
	public boolean checkInventory(ProductId itemId) {
		try {
			Product p = getProduct(itemId);
			if(p.getStockQuantity().get() > 0) {
				return true;
			}else {
				//check for all parts
				ComprisingPart[] parts = p.getComprisingParts();
				for(int c=0; c<parts.length;c++) {
					PartId partId = parts[c].getPart();
					Long quantity = parts[c].getQuantity();
					Part part = getPart(partId);
					if(part.getStockQuantity().get() < quantity) {
						return false;
					}
				}
				return true;
			}
		}catch(Exception e) {
			return false;
		}
	}
}

