package com.remotegroup.inventory.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.remotegroup.inventory.exceptions.PartNotFoundByProductException;
import com.remotegroup.inventory.exceptions.PartNotFoundException;
import com.remotegroup.inventory.exceptions.ProductNotFoundException;
import com.remotegroup.inventory.part.domain.Part;
import com.remotegroup.inventory.part.persistence.PartRepository;
import com.remotegroup.inventory.product.domain.Product;
import com.remotegroup.inventory.product.persistence.ProductRepository;

@Service
public class InventoryServiceImpl implements InventoryService{

	ProductRepository productRepository;
	PartRepository partRepository;
	
	InventoryServiceImpl(ProductRepository productRepository, PartRepository partRepository){
		this.partRepository = partRepository;
		this.productRepository = productRepository;
	}
	
	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product createProduct(Product p) {
		return productRepository.save(p);
	}

	@Override
	public Product updateProduct(Product p, Long id) {
		return productRepository.findById(id)
		      	.map(Product -> {
					Product.setName(p.getName());
					Product.setPrice(p.getPrice());
		            Product.setComment(p.getComment());
		        return productRepository.save(Product);
		      })
		      	.orElseGet(() -> {
		        	p.setId(id);
		        	return productRepository.save(p);
		      });
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public Product getProduct(Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return productRepository.findById(id).get();
			
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
		  return partRepository.findAll();
	}

	@Override
	public Part createPart(Part p) {
		return partRepository.save(p);
	}

	@Override
	public Part updatePart(Part p, Long id) {
		return partRepository.findById(id)
		      	.map(Part -> {
					Part.setSupplierId(p.getSupplierId());
		            Part.setName(p.getName());
		            Part.setDescription(p.getDescription());
		        return partRepository.save(Part);
		      })
		      	.orElseGet(() -> {
		        	p.setId(id);
		        	return partRepository.save(p);
		      });
	}

	@Override
	public void deletePart(Long id) {
		partRepository.deleteById(id);
		
	}

	@Override
	public Part getPart(Long id) {
		try {
			//return repository.getReferenceById(id); This function lazy loads and causes errors, so changed to below
			return partRepository.findById(id).get();
			
		}catch(Exception e) {
			throw new PartNotFoundException(id);
		}
	}
	
	@Override
	public Long getPartSupplier(Long id) {
		Part chosenPart = partRepository.findById(id).orElseThrow(RuntimeException::new);
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
	
	/*
	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	
	@Override
	public void procurementRequest(BackOrderSale b) {
		try {
			kafkaTemplate.send("remotegroup", b);
		}catch(Exception e) {
			
		}
	}*/
}

