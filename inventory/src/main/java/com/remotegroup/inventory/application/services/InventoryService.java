package com.remotegroup.inventory.application.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.remotegroup.inventory.domain.model.aggregates.Part;
import com.remotegroup.inventory.domain.model.aggregates.Product;
import com.remotegroup.inventory.domain.model.commands.CreatePartCommand;
import com.remotegroup.inventory.domain.model.commands.CreateProductCommand;
import com.remotegroup.inventory.domain.model.services.IInventoryService;
import com.remotegroup.inventory.exceptions.PartNotFoundByProductException;
import com.remotegroup.inventory.exceptions.PartNotFoundException;
import com.remotegroup.inventory.exceptions.ProductNotFoundException;
import com.remotegroup.inventory.infrastructure.persistence.PartRepository;
import com.remotegroup.inventory.infrastructure.persistence.ProductRepository;
import com.remotegroup.inventory.interfaces.rest.controllers.PartModelAssembler;

@Service
public class InventoryService implements IInventoryService{

	@Autowired private final ProductRepository prRepo;
	@Autowired private final PartRepository paRepo;
	@Autowired private final PartModelAssembler partAssembler;
	
	InventoryService(ProductRepository prRepo, PartRepository paRepo){
		this.paRepo = paRepo;
		this.prRepo = prRepo;
		this.partAssembler = partAssembler;
	}
	
	@Override
	public List<Product> getProducts() {
		return prRepo.findAll();
	}

	@Override
	public Product createProduct(CreateProductCommand createProductCommand) {
		String supplierIdStr = UUID.randomUUID().toString().toUpperCase();
		createProductCommand.setProductId(supplierIdStr);

		return prRepo.save(new Product(createProductCommand));
	}

	@Override
	public Product updateProduct(Product p, Long id) {
		return prRepo.findById(id)
		      	.map(Product -> {
					Product.setName(p.getName());
					Product.setPrice(p.getPrice());
		            Product.setComment(p.getComment());
		        return prRepo.save(Product);
		      })
		      	.orElseGet(() -> {
		        	p.setId(id);
		        	return prRepo.save(p);
		      });
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
		String supplierIdStr = UUID.randomUUID().toString().toUpperCase();
		createPartCommand.setPartId(supplierIdStr);

		return paRepo.save(new Part(createPartCommand));
	}

	@Override
	public Part updatePart(Part p, Long id) {
		return paRepo.findById(id)
		      	.map(Part -> {
					Part.setSupplierId(p.getSupplierId());
		            Part.setName(p.getName());
		            Part.setDescription(p.getDescription());
		        return paRepo.save(Part);
		      })
		      	.orElseGet(() -> {
		        	p.setId(id);
		        	return paRepo.save(p);
		      });
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
	public Long getPartSupplier(Long id) {
		Part chosenPart = getPart(id);
		return chosenPart.getSupplierId();
	}

	@Override
	public boolean checkInventory(Long itemId) {
		try {
			Product p = getProduct(itemId);
			if(p.getStockQuantity() > 0) {
				return true;
			}else {
				//check for all parts
				Long[][] parts = p.getComprisingParts();
				for(int c=0; c<parts.length;c++) {
					Long partId = parts[c][0];
					Long quantity = parts[c][1];
					Part part = getPart(partId);
					if(part.getStockQuantity() < quantity) {
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

