package com.remotegroup.inventory.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.remotegroup.inventory.domain.model.aggregates.ComprisingPart;
import com.remotegroup.inventory.domain.model.aggregates.Part;
import com.remotegroup.inventory.domain.model.aggregates.PartId;
import com.remotegroup.inventory.domain.model.aggregates.Product;
import com.remotegroup.inventory.domain.model.aggregates.ProductId;
import com.remotegroup.inventory.domain.model.commands.CreatePartCommand;
import com.remotegroup.inventory.domain.model.commands.CreateProductCommand;
import com.remotegroup.inventory.domain.model.services.IInventoryService;
import com.remotegroup.inventory.domain.model.valueobjects.SupplierId;
import com.remotegroup.inventory.exceptions.PartNotFoundByProductException;
import com.remotegroup.inventory.exceptions.PartNotFoundException;
import com.remotegroup.inventory.exceptions.ProductNotFoundException;
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
	public Product updateProduct(Product p, ProductId id) {
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("productId", match->match.exact());
		
		Product pExample = new Product();
		pExample.setProductId(id);
		Example<Product> example = Example.of(pExample, matcher);
		
		List<Product> returnProducts =  prRepo.findAll(example);
		
		Product product = returnProducts.get(0);
		
		product.setName(p.getName());
		product.setPrice(p.getPrice());
		product.setComment(p.getComment());
		product.setComprisingParts(p.getComprisingParts());
		product.setStockQuantity(p.getStockQuantity());
		return prRepo.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		prRepo.deleteById(id);
		
	}

	@Override
	public Product getProduct(Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return prRepo.findById(id).get();
			
		}catch(Exception e) {
			throw new ProductNotFoundException(id);
		}
	}

	@Override
	public List<Part> getPartByProduct(Long id){
		try {
			Product p = getProduct(id);

			Long[][] parts = p.getComprisingParts();

			List<Part> pList = new ArrayList<Part>();
			for(int c=0; c<parts.length; c++) {
				Long partId = parts[c][0];
				pList.add(getPart(partId));
			}
			return pList;
		}catch(Exception e) {
			throw new PartNotFoundByProductException(id);
		}
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
	public Part updatePart(Part p, PartId id) {
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("partId", match->match.exact());
		
		Part sExample = new Part();
		sExample.setPartId(id);
		Example<Part> example = Example.of(sExample, matcher);
		
		List<Part> returnParts =  paRepo.findAll(example);
		
		Part part = returnParts.get(0);
		
		part.setSupplierId(p.getSupplierId());
		part.setName(p.getName());
		part.setDescription(p.getDescription());
		part.setStockQuantity(p.getStockQuantity());
		return paRepo.save(part);
	}

	@Override
	public void deletePart(Long id) {
		paRepo.deleteById(id);
		
	}

	@Override
	public Part getPart(Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return paRepo.findById(id).get();
			
		}catch(Exception e) {
			throw new PartNotFoundException(id);
		}
	}
	
	@Override
	public SupplierId getPartSupplier(Long id) {
		Part chosenPart = getPart(id);
		return chosenPart.getSupplierId();
	}

	@Override
	public boolean checkInventory(Long itemId) {
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
	
	
	/*@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	
	@Override
	public void procurementRequest(BackOrderSale b) {
		try {
			kafkaTemplate.send("procurement", b);
		}catch(Exception e) {
			
		}
	}*/
}

